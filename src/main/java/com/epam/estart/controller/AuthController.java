package com.epam.estart.controller;

import com.epam.estart.dto.User;
import com.epam.estart.security.JwtDto;
import com.epam.estart.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PreAuthorize("permitAll()")
  @PostMapping
  public JwtDto login(@RequestBody User user) {
    return authService.login(user);
  }
}
