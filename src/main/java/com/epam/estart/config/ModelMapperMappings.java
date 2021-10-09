package com.epam.estart.config;

import com.epam.estart.dto.User;
import com.epam.estart.entity.UserEntity;
import com.epam.estart.entity.UserRoleEntity;
import com.epam.estart.entity.UserTagEntity;
import java.util.Set;
import java.util.stream.Collectors;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

public final class ModelMapperMappings {
  private ModelMapperMappings() {

  }

  public static void userMappings(ModelMapper modelMapper) {
    modelMapper.typeMap(User.class, UserEntity.class)
        .addMappings(mapping -> mapping.using(stringToUserRoleConverter()).map(User::getRoles, UserEntity::setRoles))
        .addMappings(mapping -> mapping.using(stringToUserTagConverter()).map(User::getTags, UserEntity::setTags));
    modelMapper.typeMap(UserEntity.class, User.class)
        .addMappings(mapping -> mapping.using(userRoleToStringConverter()).map(UserEntity::getRoles, User::setRoles))
        .addMappings(mapping -> mapping.using(userTagToStringConverter()).map(UserEntity::getTags, User::setTags));
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
