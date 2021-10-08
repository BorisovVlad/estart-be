package com.epam.estart.service;

import java.time.Instant;

public interface DateSupplier {

  Instant current();
}
