package com.epam.estart.controller.exception;

import com.epam.estart.dto.error.ErrorResponseBody;
import com.epam.estart.dto.error.ErrorType;
import com.epam.estart.dto.error.InnerError;
import com.epam.estart.dto.error.ServiceError;
import com.epam.estart.exception.NotFoundException;
import com.epam.estart.exception.SecurityException;
import com.epam.estart.exception.ServerException;
import com.epam.estart.exception.ValidationException;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.apache.logging.log4j.Level.DEBUG;
import static org.apache.logging.log4j.Level.WARN;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

  private static List<InnerError> getInnerErrors(BindingResult bindingResult) {
    Stream<InnerError> fieldErrorsStream = bindingResult.getFieldErrors().stream()
        .map(error -> InnerError.of(
            String.format("%s.%s", error.getObjectName(), error.getField()),
            String.format("%s %s", error.getField(), error.getDefaultMessage())
        ));
    Stream<InnerError> objErrorsStream = bindingResult.getGlobalErrors().stream()
        .map(error -> InnerError.of(error.getObjectName(), error.getDefaultMessage()));
    return Stream.concat(fieldErrorsStream, objErrorsStream).collect(toList());
  }

  private static void logException(Level logLevel, Exception exception) {
    log.log(logLevel, "Handling exception", exception);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = ValidationException.class)
  public ErrorResponseBody validationHandler(ValidationException ex) {
    logException(DEBUG, ex);
    return buildResponseBody(ex, ErrorType.VALIDATION, ex.getServiceError());
  }

  @ResponseStatus(HttpStatus.FORBIDDEN)
  @ExceptionHandler(value = SecurityException.class)
  public ErrorResponseBody securityHandler(SecurityException ex) {
    logException(DEBUG, ex);
    return buildResponseBody(ex, ErrorType.SECURITY, ex.getServiceError());
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = NotFoundException.class)
  public ErrorResponseBody requestHandler(NotFoundException ex) {
    logException(DEBUG, ex);
    return buildResponseBody(ex, ErrorType.REQUEST, ex.getServiceError());
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = ServerException.class)
  public ErrorResponseBody serverHandler(ServerException ex) {
    logException(DEBUG, ex);
    return buildResponseBody(ex, ErrorType.SERVER, ex.getServiceError());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = IllegalStateException.class)
  public ErrorResponseBody illegalStateHandler(IllegalStateException exception) {
    logException(DEBUG, exception);
    return buildResponseBody(exception, ErrorType.VALIDATION, ServiceError.GENERIC_ERROR);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(value = IllegalArgumentException.class)
  public ErrorResponseBody illegalStateHandler(IllegalArgumentException exception) {
    logException(DEBUG, exception);
    return buildResponseBody(exception, ErrorType.VALIDATION, ServiceError.GENERIC_ERROR);
  }

  @ResponseStatus(BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponseBody handleConstraintViolation(MethodArgumentNotValidException exception) {
    BindingResult bindingResult = exception.getBindingResult();
    logException(DEBUG, exception);
    return ErrorResponseBody.builder()
        .type(ErrorType.VALIDATION.name().toLowerCase(Locale.ROOT))
        .i18nKey(ServiceError.VALIDATION_FAILED.getValue())
        .message("Validation errors: " + bindingResult.getErrorCount())
        .errors(getInnerErrors(bindingResult))
        .build();
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public ErrorResponseBody handleAllOtherExceptions(Exception exception) {
    logException(WARN, exception);
    return buildResponseBody(exception, ErrorType.SERVER, ServiceError.GENERIC_ERROR);
  }

  private ErrorResponseBody buildResponseBody(Exception ex, ErrorType errorType, ServiceError serviceError) {
    log.debug(ex.getMessage(), ex);
    return ErrorResponseBody.builder()
        .type(errorType.name().toLowerCase())
        .i18nKey(serviceError.getValue())
        .message(ex.getLocalizedMessage())
        .build();
  }
}
