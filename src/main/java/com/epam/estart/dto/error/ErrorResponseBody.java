package com.epam.estart.dto.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseBody {

  private String type;
  private String message;
  private String i18nKey;
  private List<InnerError> errors;
}
