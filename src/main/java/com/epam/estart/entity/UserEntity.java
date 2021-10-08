package com.epam.estart.entity;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.experimental.Accessors;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = "user_table")
@Accessors(chain = true)
public class UserEntity implements AbstractEntity<UUID> {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID id;
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private String aboutMe;
  private String hardSkills;
  private String mainRole;
  @OneToMany(mappedBy = "userId",fetch = FetchType.EAGER)
  private Set<UserRoleEntity> roles;
  @OneToMany(mappedBy = "userId",fetch = FetchType.EAGER)
  private Set<UserTagEntity> tags;
}
