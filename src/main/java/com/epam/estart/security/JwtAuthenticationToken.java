package com.epam.estart.security;

import com.epam.estart.dto.User;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

@Data
public class JwtAuthenticationToken implements Authentication {
  @Getter
  private String jwt;
  private boolean authenticated;
  private Principal principal;
  private User user;

  public JwtAuthenticationToken(String jwt) {
    this.jwt = jwt;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.emptyList();
  }

  @Override
  public Object getCredentials() {
    return null;
  }

  @Override
  public Object getDetails() {
    return user;
  }

  public void setDetails(User user) {
    this.user = user;
  }

  @Override
  public Object getPrincipal() {
    return principal;
  }

  public void setPrincipal(Principal principal) {
    this.principal = principal;
  }

  @Override
  public boolean isAuthenticated() {
    return authenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    this.authenticated = isAuthenticated;
  }

  @Override
  public String getName() {
    return principal.getName();
  }
}
