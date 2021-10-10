package com.epam.estart.security;

import static com.epam.estart.dto.error.ServiceError.NO_SUCH_ENTITY;
import static org.springframework.util.StringUtils.hasText;

import com.epam.estart.config.properties.SecurityProperties;
import com.epam.estart.entity.UserEntity;
import com.epam.estart.exception.NotFoundException;
import com.epam.estart.exception.ValidationException;
import com.epam.estart.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtProvider {

  private final UserRepository userRepository;
  private final SecurityProperties securityProperties;

  @Transactional
  public String generateToken(String email) {
    UserEntity userEntity = userRepository.findByEmailIgnoreCase(email)
        .orElseThrow(() ->
            new NotFoundException(NO_SUCH_ENTITY, "User with email " + email + " not found"));
    Instant now = Instant.now();
    String token = Jwts.builder()
        .setClaims(generateJwtClaims(userEntity))
        .setIssuedAt(Date.from(now))
        .setExpiration(Date.from(now.plusSeconds(securityProperties.getJwtExpiration())))
        .signWith(SignatureAlgorithm.HS512, securityProperties.getJwtSecret())
        .compact();
    userRepository.save(userEntity);
    return token;
  }

  @Transactional
  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(securityProperties.getJwtSecret()).parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException e) {
      log.error("Token expired");
    } catch (UnsupportedJwtException e) {
      log.error("Unsupported jwt");
    } catch (MalformedJwtException e) {
      log.error("Malformed jwt");
    } catch (SignatureException e) {
      log.error("Invalid signature");
    } catch (Exception e) {
      log.error("invalid token");
    }
    return false;
  }

  public String getEmailFromToken(String token) {
    Claims claims = Jwts.parser().setSigningKey(securityProperties.getJwtSecret()).parseClaimsJws(token).getBody();
    if (claims.get("email") != null) {
      return claims.get("email").toString();
    } else {
      throw new ValidationException(NO_SUCH_ENTITY, "Email inside token not found");
    }
  }

  public String getIdFromToken(String token) {
    Claims claims = Jwts.parser().setSigningKey(securityProperties.getJwtSecret()).parseClaimsJws(token).getBody();
    if (claims.get("id") != null) {
      return claims.get("id").toString();
    } else {
      throw new ValidationException(NO_SUCH_ENTITY, "Id inside token not found");
    }
  }

  public Optional<String> getTokenFromRequest(HttpServletRequest request) {
    String bearer = request.getHeader("Authorization");
    if (hasText(bearer) && bearer.startsWith("Bearer ")) {
      return Optional.of(bearer.substring(7));
    }
    return Optional.empty();
  }

  private Map<String, Object> generateJwtClaims(UserEntity userEntity) {
    Map<String, Object> claims = new LinkedHashMap<>();
    claims.put("id", userEntity.getId());
    claims.put("email", userEntity.getEmail());
    return claims;
  }
}
