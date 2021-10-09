package com.epam.estart.dto;

import java.util.UUID;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(exclude = {"id"})
public class UserTag implements AbstractDTO<Long> {
  private Long id;
  private UUID userId;
  private String name;
}
