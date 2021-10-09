package com.epam.estart.service;

import com.epam.estart.dto.User;
import com.epam.estart.exception.ValidationException;
import com.epam.estart.security.JwtDto;
import com.epam.estart.security.JwtProvider;
import com.epam.estart.service.impl.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.epam.estart.dto.error.ServiceError.USER_ALREADY_EXISTS;
import static com.epam.estart.dto.error.ServiceError.WRONG_CREDENTIALS;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserService userService;
  private final JwtProvider jwtProvider;

  public JwtDto register(User user) {
    if (!userService.isExistsByEmail(user.getEmail())) {
      userService.create(user);
      return new JwtDto(jwtProvider.generateToken(user.getEmail()));
    } else {
      throw new ValidationException(USER_ALREADY_EXISTS, "User with these credentials already exists: "
          + user.getEmail());
    }
  }

  public JwtDto login(User user) {
    if (userService.isExistsByEmailAndPassword(user.getEmail(), user.getPassword())) {
      return new JwtDto(jwtProvider.generateToken(user.getEmail()));
    } else {
      throw new ValidationException(WRONG_CREDENTIALS, "Username or password is incorrect.");
    }
  }
}
