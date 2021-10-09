package com.epam.estart.exception;


import com.epam.estart.dto.error.ServiceError;

public class NotFoundException extends ServiceException {

  public NotFoundException(ServiceError error) {
    super(error);
  }

  public NotFoundException(ServiceError error, String message) {
    super(error, message);
  }

  public NotFoundException(ServiceError error, String message, Throwable cause) {
    super(error, message, cause);
  }
}
