package com.epam.estart.config;

import com.epam.estart.dto.Project;
import com.epam.estart.dto.User;
import com.epam.estart.entity.MemberOnBoardEntity;
import com.epam.estart.entity.ProjectEntity;
import com.epam.estart.entity.ProjectTagEntity;
import com.epam.estart.entity.UserEntity;
import com.epam.estart.entity.UserRoleEntity;
import com.epam.estart.entity.UserTagEntity;
import com.epam.estart.entity.VacantPlaceEntity;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public final class ModelMapperMappings {
  private ModelMapperMappings() {
  }

  public static void userMappings(ModelMapper model) {
    model.typeMap(User.class, UserEntity.class)
        .addMappings(mapping -> mapping.using(stringToUserRoleConverter()).map(User::getRoles, UserEntity::setRoles))
        .addMappings(mapping -> mapping.using(stringToUserTagConverter()).map(User::getTags, UserEntity::setTags));
    model.typeMap(UserEntity.class, User.class)
        .addMappings(mapping -> mapping.using(userRoleToStringConverter()).map(UserEntity::getRoles, User::setRoles))
        .addMappings(mapping -> mapping.using(userTagToStringConverter()).map(UserEntity::getTags, User::setTags));
  }

  public static void projectMappings(ModelMapper model) {
    model.typeMap(Project.class, ProjectEntity.class)
        .addMappings(mapping -> mapping.using(stringToProjectTagConverter())
            .map(Project::getTags, ProjectEntity::setTags))
        .addMappings(mapping -> mapping.using(stringToMemberOnBoardConverter())
            .map(Project::getMembersOnBoard, ProjectEntity::setMembersOnBoard))
        .addMappings(mapping -> mapping.using(stringToVacantPlacesConverter())
            .map(Project::getVacantPlaces, ProjectEntity::setVacantPlaces));
    model.typeMap(ProjectEntity.class, Project.class)
        .addMappings(mapping -> mapping.using(projectTagToStringConverter())
            .map(ProjectEntity::getTags, Project::setTags))
        .addMappings(mapping -> mapping.using(memberOnBoardToStringConverter())
            .map(ProjectEntity::getMembersOnBoard, Project::setMembersOnBoard))
        .addMappings(mapping -> mapping.using(vacantPlacesToStringConverter())
            .map(ProjectEntity::getVacantPlaces, Project::setVacantPlaces));
  }

  private static Converter<Set<String>, Set<ProjectTagEntity>> stringToProjectTagConverter() {
    return context -> context.getSource() == null ? null : context.getSource().stream()
        .map(s -> new ProjectTagEntity().setName(s))
        .collect(Collectors.toSet());
  }

  private static Converter<Set<ProjectTagEntity>, Set<String>> projectTagToStringConverter() {
    return context -> context.getSource() == null ? null : context.getSource().stream()
        .map(ProjectTagEntity::getName)
        .collect(Collectors.toSet());
  }

  private static Converter<Set<String>, Set<MemberOnBoardEntity>> stringToMemberOnBoardConverter() {
    return context -> context.getSource() == null ? null : context.getSource().stream()
        .map(s -> new MemberOnBoardEntity().setRole(s))
        .collect(Collectors.toSet());
  }

  private static Converter<Set<MemberOnBoardEntity>, Set<String>> memberOnBoardToStringConverter() {
    return context -> context.getSource() == null ? null : context.getSource().stream()
        .map(MemberOnBoardEntity::getRole)
        .collect(Collectors.toSet());
  }

  private static Converter<Set<String>, Set<VacantPlaceEntity>> stringToVacantPlacesConverter() {
    return context -> context.getSource() == null ? null : context.getSource().stream()
        .map(s -> new VacantPlaceEntity().setRole(s))
        .collect(Collectors.toSet());
  }

  private static Converter<Set<VacantPlaceEntity>, Set<String>> vacantPlacesToStringConverter() {
    return context -> context.getSource() == null ? null : context.getSource().stream()
        .map(VacantPlaceEntity::getRole)
        .collect(Collectors.toSet());
  }


  private static Converter<Set<String>, Set<UserRoleEntity>> stringToUserRoleConverter() {
    return context -> context.getSource() == null ? null : context.getSource().stream()
        .map(s -> new UserRoleEntity().setName(s))
        .collect(Collectors.toSet());
  }

  private static Converter<Set<UserRoleEntity>, Set<String>> userRoleToStringConverter() {
    return context -> context.getSource() == null ? null : context.getSource().stream()
        .map(UserRoleEntity::getName)
        .collect(Collectors.toSet());
  }

  private static Converter<Set<String>, Set<UserTagEntity>> stringToUserTagConverter() {
    return context -> context.getSource() == null ? null : context.getSource().stream()
        .map(s -> new UserTagEntity().setName(s))
        .collect(Collectors.toSet());
  }

  private static Converter<Set<UserTagEntity>, Set<String>> userTagToStringConverter() {
    return context -> context.getSource() == null ? null : context.getSource().stream()
        .map(UserTagEntity::getName)
        .collect(Collectors.toSet());
  }
}
