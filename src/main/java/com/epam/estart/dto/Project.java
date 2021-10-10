package com.epam.estart.dto;

import com.epam.estart.entity.Stage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;
import java.util.HashSet;
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
  private String language;
  private Stage stage;
  private String aboutProject;
  private Set<String> tags = new HashSet<>();
  private Set<String> vacantPlaces = new HashSet<>();
  private Set<String> membersOnBoard = new HashSet<>();
  @JsonIgnore
  private Instant createdAt;
}
