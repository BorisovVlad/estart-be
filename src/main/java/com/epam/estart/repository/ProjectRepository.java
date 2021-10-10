package com.epam.estart.repository;

import com.epam.estart.entity.ProjectEntity;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends AbstractRepository<ProjectEntity, UUID> {
  List<ProjectEntity> findAll();

  @Query("select p from ProjectEntity p "
      + "join p.tags tags on tags.projectId = p.id "
      + "join p.vacantPlaces vacantPlaces on vacantPlaces.projectId = p.id "
      + "where (tags.name in :tagsName or :isTagPresent = true ) "
      + "and (p.stage in :stages or :isStagesPresent = true ) "
      + "and (vacantPlaces.role in :vacantPlaceRoles or :isVacantRolePresent = true ) "
      + "GROUP BY p.id "
      + "having (size(p.tags) >= :tagsSize) "
      + "and (size(p.vacantPlaces) >= :vacantPlacesSize ) "
      + "order by p.createdAt")
  Page<ProjectEntity> findAllByFilter(Set<String> tagsName, boolean isTagPresent, int tagsSize,
                                      Set<String> stages, boolean isStagesPresent,
                                      Set<String> vacantPlaceRoles, boolean isVacantRolePresent, int vacantPlacesSize,
                                      Pageable pageable);

  List<ProjectEntity> getAllByOwnerId(UUID ownerId);
}
