package com.epam.estart.exception;

import com.epam.estart.dto.error.ServiceError;

public class ValidationException extends ServiceException {

  public ValidationException(ServiceError error) {
    super(error);
  }

  public ValidationException(ServiceError error, String message) {
    super(error, message);
  }

  public ValidationException(ServiceError error, String message, Throwable cause) {
    super(error, message, cause);
  }
}
