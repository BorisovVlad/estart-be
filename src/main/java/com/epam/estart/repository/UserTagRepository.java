package com.epam.estart.repository;

import com.epam.estart.entity.UserTagEntity;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTagRepository extends AbstractRepository<UserTagEntity, Long> {
  void deleteAllByUserId(UUID userId);
}
