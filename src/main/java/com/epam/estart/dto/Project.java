package com.epam.estart.dto;

import com.epam.estart.entity.Language;
import com.epam.estart.entity.Stage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Project implements AbstractDTO<UUID> {
  private UUID id;
  private UUID ownerId;
  private String name;
  private String email;
  private String phone;
  private String image;
  private Language language;
  private Stage stage;
  private String aboutProject;
  private Set<String> tags;
  private Set<String> vacantPlaces;
  private Set<String> membersOnBoard;
  @JsonIgnore
  private Instant createdAt;
}
