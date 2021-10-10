package com.epam.estart.dto.error;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
