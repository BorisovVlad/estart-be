package com.epam.estart.service.impl;

import com.epam.estart.dto.Project;
import com.epam.estart.entity.MemberOnBoardEntity;
import com.epam.estart.entity.ProjectEntity;
import com.epam.estart.entity.ProjectTagEntity;
import com.epam.estart.entity.VacantPlaceEntity;
import com.epam.estart.repository.ProjectRepository;
import com.epam.estart.security.AuthorisedUser;
import com.epam.estart.service.AuthenticationService;
import com.epam.estart.service.DateSupplier;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends AbstractService<UUID, Project, ProjectEntity, ProjectRepository> {
  private final ProjectTagService projectTagService;
  private final VacantPlaceService vacantPlacesService;
  private final MemberOnBoardService memberOnBoardService;
  private final AuthenticationService authenticationService;
  private final DateSupplier dateSupplier;


  ProjectService(ProjectRepository repository, ProjectTagService projectTagService,
                 VacantPlaceService vacantPlacesService,
                 MemberOnBoardService memberOnBoardService,
                 DateSupplier dateSupplier,
                 AuthenticationService authenticationService) {
    super(repository);
    this.projectTagService = projectTagService;
    this.vacantPlacesService = vacantPlacesService;
    this.memberOnBoardService = memberOnBoardService;
    this.dateSupplier = dateSupplier;
    this.authenticationService = authenticationService;
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
    AuthorisedUser authenticatedUser = authenticationService.getAuthenticatedUser();
    ProjectEntity projectEntity = modelMapper.map(project, ProjectEntity.class)
        .setCreatedAt(dateSupplier.current())
        .setOwnerId(authenticatedUser.getId());
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
        .setEmail(project.getEmail())
        .setPhone(project.getPhone())
        .setImage(project.getImage())
        .setLanguage(project.getLanguage())
        .setStage(project.getStage())
        .setStack(project.getStack())
        .setAboutProject(project.getAboutProject())
        .setTags(getNewProjectTags(project, projectEntity))
        .setVacantPlaces(getNewVacantPlaces(project, projectEntity))
        .setMembersOnBoard(getNewMembersOnBoard(project, projectEntity))
        .setCreatedAt(dateSupplier.current());

    return modelMapper.map(repository.save(projectEntity), Project.class);
  }

  public List<Project> getAllProjects() {
    return repository.findAll().stream()
        .map(entity -> modelMapper.map(entity, Project.class))
        .collect(Collectors.toList());
  }

  public Page<Project> getAllProjectsByFilter(Set<String> vacantPlaceRoles,
                                              Set<String> stages,
                                              Set<String> tagNames,
                                              Pageable pageable) {
    return repository.findAllByFilter(
        tagNames, tagNames.isEmpty(), tagNames.size(),
        stages, stages.isEmpty(),
        vacantPlaceRoles, vacantPlaceRoles.isEmpty(), vacantPlaceRoles.size(),
        pageable
    ).map(projectEntity -> modelMapper.map(projectEntity,
        Project.class));
  }

  public List<Project> getAllProjectsByOwnerId(UUID id) {
    return repository.getAllByOwnerId(id).stream()
        .map(projectEntity -> modelMapper.map(projectEntity, Project.class))
        .collect(Collectors.toList());
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

  private Set<VacantPlaceEntity> getNewVacantPlaces(Project project, ProjectEntity projectEntity) {
    Set<VacantPlaceEntity> oldVacantPlaces = projectEntity.getVacantPlaces();
    Set<VacantPlaceEntity> newVacantPlaces = project.getVacantPlaces().stream()
        .map(tag -> new VacantPlaceEntity().setProjectId(project.getId()))
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
