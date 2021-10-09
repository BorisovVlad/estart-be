package com.epam.estart.config;

import static com.epam.estart.config.ModelMapperMappings.projectMappings;
import static com.epam.estart.config.ModelMapperMappings.userMappings;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();
    userMappings(modelMapper);
    projectMappings(modelMapper);
    return modelMapper;
  }
}
