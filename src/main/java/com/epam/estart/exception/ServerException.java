package com.epam.estart.exception;

import com.epam.estart.dto.error.ServiceError;

public class ServerException extends ServiceException {

  public ServerException(ServiceError error) {
    super(error);
  }

  public ServerException(ServiceError error, String message) {
    super(error, message);
  }

  public ServerException(ServiceError error, String message, Throwable cause) {
    super(error, message, cause);
  }
}
