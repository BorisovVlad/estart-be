package com.epam.estart.controller;

import com.epam.estart.dto.User;
import com.epam.estart.service.impl.UserService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
  private final UserService userService;

  @GetMapping("/{id}")
  public User getById(@PathVariable UUID id) {
    return userService.getById(id);
  }

  @PostMapping
  public User create(@RequestBody User user) {
    return userService.create(user);
  }
}
