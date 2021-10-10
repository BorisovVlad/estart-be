package com.epam.estart.exception;

import com.epam.estart.dto.error.ServiceError;

public class SecurityException extends ServiceException {

  public SecurityException(ServiceError error) {
    super(error);
  }

  public SecurityException(ServiceError error, String message) {
    super(error, message);
  }

  public SecurityException(ServiceError error, String message, Throwable cause) {
    super(error, message, cause);
  }
}
