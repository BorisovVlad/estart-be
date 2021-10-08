package com.epam.estart.service.impl;

import com.epam.estart.service.DateSupplier;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class DefaultDateSupplier implements DateSupplier {
  @Override
  public Instant current() {
    return Instant.now();
  }
}
