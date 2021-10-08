package com.epam.estart.service.impl;

import com.epam.estart.dto.UserTag;
import com.epam.estart.entity.UserTagEntity;
import com.epam.estart.repository.UserTagRepository;
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
}
