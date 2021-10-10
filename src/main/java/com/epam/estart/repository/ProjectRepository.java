package com.epam.estart.repository;

import com.epam.estart.entity.ProjectEntity;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends AbstractRepository<ProjectEntity, UUID> {
  List<ProjectEntity> findAll();

  @Query(value = "SELECT DISTINCT on (created_at) * FROM projects project "
      + "JOIN project_tag tag on tag.project_id = project.id "
      + "JOIN vacant_places vacant on vacant.project_id = project.id "
      + "WHERE (tag.name in :tags or :isTagPresent ) "
      + "AND (project.stage in :stages or :isStagesPresent ) "
      + "AND (vacant.role in :vacantPlaces or :isVacantRolePresent ) "
      + "ORDER BY created_at DESC;", nativeQuery = true)
  List<ProjectEntity> findAllByFilter(Set<String> vacantPlaces, boolean isVacantRolePresent,
                                      Set<String> stages, boolean isStagesPresent,
                                      Set<String> tags, boolean isTagPresent);

  List<ProjectEntity> getAllByOwnerId(UUID ownerId);
}
