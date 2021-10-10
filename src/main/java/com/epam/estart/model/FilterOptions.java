package com.epam.estart.model;

import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.PageRequest;

@Data
@Accessors(chain = true)
public class FilterOptions {
  private int page = 0;
  private int size = 10;
  private Set<String> vacantPlaces = new HashSet<>();
  private Set<String> stages = new HashSet<>();
  private Set<String> tags = new HashSet<>();

  public PageRequest getPageRequest() {
    return PageRequest.of(page, size);
  }
}
