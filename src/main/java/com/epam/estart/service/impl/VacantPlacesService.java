package com.epam.estart.service.impl;

import com.epam.estart.dto.VacantPlace;
import com.epam.estart.entity.ProjectEntity;
import com.epam.estart.entity.VacantPlacesEntity;
import com.epam.estart.repository.VacantPlacesRepository;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class VacantPlacesService
    extends AbstractService<Long, VacantPlace, VacantPlacesEntity, VacantPlacesRepository> {

  VacantPlacesService(VacantPlacesRepository repository) {
    super(repository);
  }

  @Override
  public Class<VacantPlacesEntity> getEntityClass() {
    return VacantPlacesEntity.class;
  }

  @Override
  public Class<VacantPlace> getDTOClass() {
    return VacantPlace.class;
  }

  public void createAllByProjectEntity(ProjectEntity projectEntity) {
    Set<VacantPlacesEntity> vacantPlaces = projectEntity.getVacantPlaces();
    vacantPlaces.forEach(vacantPlacesEntity -> vacantPlacesEntity.setProjectId(projectEntity.getId()));
    repository.saveAll(vacantPlaces);
  }

  public void removeAll(Set<VacantPlacesEntity> vacantPlacesEntities) {
    repository.deleteAll(vacantPlacesEntities);
  }
}
