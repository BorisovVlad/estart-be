package com.epam.estart.repository;

import com.epam.estart.entity.ProjectEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends AbstractRepository<ProjectEntity, UUID> {
  List<ProjectEntity> findAll();
}
