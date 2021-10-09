package com.epam.estart.service.impl;

import com.epam.estart.dto.UserRole;
import com.epam.estart.entity.UserEntity;
import com.epam.estart.entity.UserRoleEntity;
import com.epam.estart.repository.UserRoleRepository;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService extends AbstractService<Long, UserRole, UserRoleEntity, UserRoleRepository> {
  UserRoleService(UserRoleRepository repository) {
    super(repository);
  }

  @Override
  public Class<UserRoleEntity> getEntityClass() {
    return UserRoleEntity.class;
  }

  @Override
  public Class<UserRole> getDTOClass() {
    return UserRole.class;
  }

  public void createAllByUserEntity(UserEntity userEntity) {
    Set<UserRoleEntity> userRoleEntities = userEntity.getRoles();
    for (UserRoleEntity userRole : userRoleEntities) {
      userRole.setUserId(userEntity.getId());
    }
    repository.saveAll(userRoleEntities);
  }

  public void removeAllByUserId(UUID userId) {
    repository.deleteAllByUserId(userId);
  }

  public void removeAll(Collection<UserRoleEntity> userRoles) {
    repository.deleteAll(userRoles);
  }
}
