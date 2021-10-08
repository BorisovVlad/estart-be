package com.epam.estart.service.impl;

import com.epam.estart.dto.User;
import com.epam.estart.dto.UserRole;
import com.epam.estart.entity.UserEntity;
import com.epam.estart.entity.UserRoleEntity;
import com.epam.estart.entity.UserTagEntity;
import com.epam.estart.repository.UserRepository;
import com.epam.estart.repository.UserRoleRepository;
import com.epam.estart.repository.UserTagRepository;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<UUID, User, UserEntity, UserRepository> {
  private final UserRoleRepository userRoleRepository;
  private final UserTagRepository userTagRepository;

  UserService(UserRepository repository, UserRoleRepository userRoleRepository, UserTagRepository userTagRepository) {
    super(repository);
    this.userRoleRepository = userRoleRepository;
    this.userTagRepository = userTagRepository;
  }

  @Override
  public Class<UserEntity> getEntityClass() {
    return UserEntity.class;
  }

  @Override
  public Class<User> getDTOClass() {
    return User.class;
  }

  @Override
  public User create(User user) {
    UserEntity userEntity = modelMapper.map(user, UserEntity.class);
    Set<UserRoleEntity> userRoleEntities = userEntity.getRoles();
    Set<UserTagEntity> userTagEntities = userEntity.getTags();
    userEntity.setRoles(null);
    userEntity.setTags(null);
    userEntity = repository.save(userEntity);
    for (UserRoleEntity userRole : userRoleEntities) {
      userRole.setUserId(userEntity.getId());
    }
    userRoleRepository.saveAll(userRoleEntities);
    for (UserTagEntity userTag : userTagEntities) {
      userTag.setUserId(userEntity.getId());
    }
    userTagRepository.saveAll(userTagEntities);
    return getById(userEntity.getId());
  }
}
