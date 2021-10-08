package com.epam.estart.service;

import com.epam.estart.entity.AbstractEntity;
import java.util.Optional;

public interface CommonService<I, E extends AbstractEntity<?>> {

  E create(E entity);

  E getById(I id);

  void update(E entity);

  void delete(E entity);

  void deleteById(I id);

  Optional<E> getOptionalById(I id);
}
