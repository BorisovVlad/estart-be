package com.epam.estart.security;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtDto {

  @JsonProperty("access-token")
  private String accessToken;

}
