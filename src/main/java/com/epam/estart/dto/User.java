package com.epam.estart.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class User implements AbstractDTO<UUID> {
  private UUID id;
  private String firstName;
  private String lastName;
  private String email;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String password;
  private String aboutMe;
  private String hardSkills;
  private String mainRole;
  private Set<String> roles = new HashSet<>();
  private Set<String> tags = new HashSet<>();
}
