package com.epam.estart.service;


import com.epam.estart.security.AuthorisedUser;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthenticationService {

  public boolean isUserAuthenticated() {
    try {
      Object user = getUserFromSecurityContext();
      return user instanceof AuthorisedUser;
    } catch (Exception ex) {
      return false;
    }
  }

  public AuthorisedUser getAuthenticatedUser() {
    Object userFromSecurityContext = getUserFromSecurityContext();
    if (userFromSecurityContext instanceof AuthorisedUser) {
      return (AuthorisedUser) userFromSecurityContext;
    }
    throw new AuthenticationServiceException("There is no authentication user inside context");
  }

  private Object getUserFromSecurityContext() {
    if (SecurityContextHolder.getContext().getAuthentication() != null) {
      return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    } else {
      throw new AuthenticationServiceException("There is no authentication user inside context");
    }
  }
}
