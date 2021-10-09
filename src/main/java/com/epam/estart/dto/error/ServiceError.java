package com.epam.estart.dto.error;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceError {

  GENERIC_ERROR("error.generic.error"),

  // type - validation
  CANNOT_BE_EDITED("error.cannot.be.edited"),
  INVALID_REQUEST("error.invalid.request"),
  VALIDATION_FAILED("error.validation.failed"),
  ENCODING_EXCEPTION("error.encoding.exception"),
  WRONG_CREDENTIALS("error.wrong.credentials"),
  EMPTY_TOKEN_IN_REQUEST("error.empty.token.in.request"),
  USER_ALREADY_EXISTS("error.user.already.exists"),

  // type - security
  ACCESS_DENIED("error.security.access.denied"),

  // type - request
  NO_SUCH_ENTITY("error.no.such.entity"),

  // type - server
  PARSE_ERROR("error.parse.error");

  @JsonValue
  private final String value;
}
