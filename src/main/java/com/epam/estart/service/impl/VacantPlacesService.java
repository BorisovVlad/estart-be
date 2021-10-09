package com.epam.estart.service.impl;

import com.epam.estart.dto.Project;
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

  public Set<VacantPlacesEntity> createAllByProjectEntity(Project project) {
    Set<VacantPlacesEntity> vacantPlaces = modelMapper.map(project, ProjectEntity.class).getVacantPlaces();
    vacantPlaces.forEach(vacantPlacesEntity -> vacantPlacesEntity.setProjectId(project.getId()));
    repository.saveAll(vacantPlaces);
    return vacantPlaces;
  }

  public void removeAll(Set<VacantPlacesEntity> vacantPlacesEntities) {
    repository.deleteAll(vacantPlacesEntities);
  }
}
