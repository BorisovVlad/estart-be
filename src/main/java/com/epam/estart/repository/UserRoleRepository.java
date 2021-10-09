package com.epam.estart.repository;

import com.epam.estart.entity.UserRoleEntity;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends AbstractRepository<UserRoleEntity, Long> {
  void deleteAllByUserId(UUID userId);
}
