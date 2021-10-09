package com.epam.estart.exception;

import com.epam.estart.dto.error.ServiceError;

public class ServiceException extends RuntimeException {
  private final ServiceError serviceError;

  public ServiceException(ServiceError error) {
    this.serviceError = error;
  }

  public ServiceException(ServiceError error, String message) {
    super(message);
    this.serviceError = error;
  }

  public ServiceException(ServiceError error, String message, Throwable cause) {
    super(message, cause);
    this.serviceError = error;
  }

  public ServiceError getServiceError() {
    return serviceError;
  }
}
