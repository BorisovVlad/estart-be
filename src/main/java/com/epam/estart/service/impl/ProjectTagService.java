package com.epam.estart.service.impl;

import com.epam.estart.dto.ProjectTag;
import com.epam.estart.entity.ProjectEntity;
import com.epam.estart.entity.ProjectTagEntity;
import com.epam.estart.repository.ProjectTagRepository;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class ProjectTagService extends AbstractService<Long, ProjectTag, ProjectTagEntity, ProjectTagRepository> {
  ProjectTagService(ProjectTagRepository repository) {
    super(repository);
  }

  @Override
  public Class<ProjectTagEntity> getEntityClass() {
    return ProjectTagEntity.class;
  }

  @Override
  public Class<ProjectTag> getDTOClass() {
    return ProjectTag.class;
  }

  public void createAllByProjectEntity(ProjectEntity projectEntity) {
    Set<ProjectTagEntity> tags = projectEntity.getTags();
    tags.forEach(projectTagEntity -> projectTagEntity.setProjectId(projectEntity.getId()));
    repository.saveAll(tags);
  }

  public void removeAll(Set<ProjectTagEntity> projectTagEntities) {
    repository.deleteAll(projectTagEntities);
  }
}
