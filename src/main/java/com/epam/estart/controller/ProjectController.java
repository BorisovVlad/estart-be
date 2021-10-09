package com.epam.estart.controller;

import com.epam.estart.dto.Project;
import com.epam.estart.service.impl.ProjectService;
import java.util.Collection;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
  private final ProjectService projectService;

  @GetMapping("/{id}")
  public Project getById(@PathVariable UUID id) {
    return projectService.getById(id);
  }

  @PostMapping
  public Project create(@RequestBody Project project) {
    return projectService.create(project);
  }

  @PutMapping
  public Project update(@RequestBody Project project) {
    return projectService.updateAndReturn(project);
  }

  @GetMapping
  public Collection<Project> getAllProjectsByFilter() {
    return projectService.getAllProjects();
  }
}
