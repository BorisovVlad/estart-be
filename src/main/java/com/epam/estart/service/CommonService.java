package com.epam.estart.service;

import com.epam.estart.dto.AbstractDTO;
import com.epam.estart.entity.AbstractEntity;
import java.util.Optional;

public interface CommonService<I, M extends AbstractDTO<I>, E extends AbstractEntity<?>> {

  M create(M entity);

  M getById(I id);

  void update(M entity);

  void delete(M entity);

  void deleteById(I id);

  Optional<M> getOptionalById(I id);
}
