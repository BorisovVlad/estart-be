package com.epam.estart.service.impl;

import com.epam.estart.dto.UserTag;
import com.epam.estart.entity.UserEntity;
import com.epam.estart.entity.UserTagEntity;
import com.epam.estart.repository.UserTagRepository;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class UserTagService extends AbstractService<Long, UserTag, UserTagEntity, UserTagRepository> {
  UserTagService(UserTagRepository repository) {
    super(repository);
  }

  @Override
  public Class<UserTagEntity> getEntityClass() {
    return UserTagEntity.class;
  }

  @Override
  public Class<UserTag> getDTOClass() {
    return UserTag.class;
  }

  public void createAllByUserEntity(UserEntity userEntity) {
    Set<UserTagEntity> userTagEntities = userEntity.getTags();
    for (UserTagEntity userTag : userTagEntities) {
      userTag.setUserId(userEntity.getId());
    }
    repository.saveAll(userTagEntities);
  }

  public void removeAllByUserId(UUID userId) {
    repository.deleteAllByUserId(userId);
  }

  public void removeAll(Collection<UserTagEntity> userTags) {
    repository.deleteAll(userTags);
  }
}
