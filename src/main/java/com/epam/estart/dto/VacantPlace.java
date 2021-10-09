package com.epam.estart.dto;

import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class VacantPlace implements AbstractDTO<Long> {
  private Long id;
  private UUID projectId;
  private String role;
}
