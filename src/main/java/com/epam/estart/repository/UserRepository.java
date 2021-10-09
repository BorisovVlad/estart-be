package com.epam.estart.repository;

import com.epam.estart.entity.UserEntity;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractRepository<UserEntity, UUID> {
}
