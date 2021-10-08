package com.epam.estart.service.impl;

import com.epam.estart.dto.UserRole;
import com.epam.estart.entity.UserRoleEntity;
import com.epam.estart.repository.UserRoleRepository;
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
}
