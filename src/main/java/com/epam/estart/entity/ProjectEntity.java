package com.epam.estart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "projects")
@Data
@Accessors(chain = true)
public class ProjectEntity implements AbstractEntity<UUID> {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;
  private UUID ownerId;
  private String name;
  @Enumerated(EnumType.STRING)
  private Stage stage;
  private String aboutProject;
  @OneToMany(mappedBy = "projectId")
  private Set<ProjectTagEntity> tags;
  @OneToMany(mappedBy = "projectId")
  private Set<VacantPlacesEntity> vacantPlaces;
  @OneToMany(mappedBy = "projectId")
  private Set<MemberOnBoardEntity> membersOnBoard;
  @JsonIgnore
  private Instant createdAt;
  @JsonIgnore
  private Instant closedAt;
}
