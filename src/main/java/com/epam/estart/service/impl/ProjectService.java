package com.epam.estart.service.impl;

import com.epam.estart.dto.Project;
import com.epam.estart.entity.MemberOnBoardEntity;
import com.epam.estart.entity.ProjectEntity;
import com.epam.estart.entity.ProjectTagEntity;
import com.epam.estart.entity.VacantPlacesEntity;
import com.epam.estart.repository.ProjectRepository;
import com.epam.estart.service.DateSupplier;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends AbstractService<UUID, Project, ProjectEntity, ProjectRepository> {
  private final ProjectTagService projectTagService;
  private final VacantPlacesService vacantPlacesService;
  private final MemberOnBoardService memberOnBoardService;
  private final DateSupplier dateSupplier;


  ProjectService(ProjectRepository repository, ProjectTagService projectTagService,
                 VacantPlacesService vacantPlacesService,
                 MemberOnBoardService memberOnBoardService, DateSupplier dateSupplier) {
    super(repository);
    this.projectTagService = projectTagService;
    this.vacantPlacesService = vacantPlacesService;
    this.memberOnBoardService = memberOnBoardService;
    this.dateSupplier = dateSupplier;
  }

  @Override
  public Class<ProjectEntity> getEntityClass() {
    return ProjectEntity.class;
  }

  @Override
  public Class<Project> getDTOClass() {
    return Project.class;
  }

  @Override
  public Project create(Project project) {
    ProjectEntity projectEntity = modelMapper.map(project.setCreatedAt(dateSupplier.current()), ProjectEntity.class);
    projectEntity = repository.save(projectEntity);
    projectEntity.setTags(projectTagService.createAllByProjectEntity(project.setId(projectEntity.getId())))
        .setVacantPlaces(vacantPlacesService.createAllByProjectEntity(project.setId(projectEntity.getId())))
        .setMembersOnBoard(memberOnBoardService.createAllByProjectEntity(project.setId(projectEntity.getId())));

    return modelMapper.map(repository.save(projectEntity), Project.class);
  }

  public Project updateAndReturn(Project project) {

    ProjectEntity projectEntity = repository.findById(project.getId())
        .orElseThrow(
            () -> new IllegalArgumentException(String.format("Project with id=%s not found!", project.getId())));

    projectEntity.setName(project.getName())
        .setStage(project.getStage())
        .setAboutProject(projectEntity.getAboutProject())
        .setTags(getNewProjectTags(project, projectEntity))
        .setVacantPlaces(getNewVacantPlaces(project, projectEntity))
        .setMembersOnBoard(getNewMembersOnBoard(project, projectEntity));

    return modelMapper.map(repository.save(projectEntity), Project.class);
  }

  private Set<ProjectTagEntity> getNewProjectTags(Project project, ProjectEntity projectEntity) {
    Set<ProjectTagEntity> oldProjectTags = projectEntity.getTags();
    Set<ProjectTagEntity> newProjectTags = project.getTags().stream()
        .map(tag -> new ProjectTagEntity().setProjectId(project.getId()))
        .collect(Collectors.toSet());
    oldProjectTags.removeAll(newProjectTags);
    projectTagService.removeAll(oldProjectTags);

    return projectTagService.createAllByProjectEntity(project.setId(projectEntity.getId()));
  }

  private Set<VacantPlacesEntity> getNewVacantPlaces(Project project, ProjectEntity projectEntity) {
    Set<VacantPlacesEntity> oldVacantPlaces = projectEntity.getVacantPlaces();
    Set<VacantPlacesEntity> newVacantPlaces = project.getVacantPlaces().stream()
        .map(tag -> new VacantPlacesEntity().setProjectId(project.getId()))
        .collect(Collectors.toSet());
    oldVacantPlaces.removeAll(newVacantPlaces);
    vacantPlacesService.removeAll(oldVacantPlaces);

    return vacantPlacesService.createAllByProjectEntity(project.setId(projectEntity.getId()));
  }

  private Set<MemberOnBoardEntity> getNewMembersOnBoard(Project project, ProjectEntity projectEntity) {
    Set<MemberOnBoardEntity> oldMembersOnBoard = projectEntity.getMembersOnBoard();
    Set<MemberOnBoardEntity> newMembersOnBoard = project.getMembersOnBoard().stream()
        .map(tag -> new MemberOnBoardEntity().setProjectId(project.getId()))
        .collect(Collectors.toSet());
    oldMembersOnBoard.removeAll(newMembersOnBoard);
    memberOnBoardService.removeAll(oldMembersOnBoard);

    return memberOnBoardService.createAllByProjectEntity(project.setId(projectEntity.getId()));
  }
}
