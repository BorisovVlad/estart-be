package com.epam.estart.repository;

import com.epam.estart.entity.UserTagEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTagRepository extends AbstractRepository<UserTagEntity, Long> {
}
