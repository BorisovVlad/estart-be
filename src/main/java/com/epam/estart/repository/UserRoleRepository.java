package com.epam.estart.repository;

import com.epam.estart.entity.UserRoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends AbstractRepository<UserRoleEntity, Long> {
}
