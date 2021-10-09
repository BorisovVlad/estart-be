package com.epam.estart;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties
@PropertySource("classpath:test-data.yml")
public class TestData {
  private String userCreateRequest;
  private String userCreateResponse;
}
