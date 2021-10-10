package com.epam.estart.service.impl;

import com.epam.estart.dto.Project;
import com.epam.estart.dto.ProjectTag;
import com.epam.estart.entity.ProjectEntity;
import com.epam.estart.entity.ProjectTagEntity;
import com.epam.estart.repository.ProjectTagRepository;
import java.util.HashSet;
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

  public Set<ProjectTagEntity> createAllByProjectEntity(Project project) {
    Set<ProjectTagEntity> tags = modelMapper.map(project, ProjectEntity.class).getTags();
    tags.forEach(projectTagEntity -> projectTagEntity.setProjectId(project.getId()));
    repository.saveAll(tags);
    return tags;
  }

  public void removeAll(Set<ProjectTagEntity> projectTagEntities) {
    repository.deleteAll(projectTagEntities);
  }
}
