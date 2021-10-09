package com.epam.estart.service.impl;

import com.epam.estart.dto.AbstractDTO;
import com.epam.estart.entity.AbstractEntity;
import com.epam.estart.repository.AbstractRepository;
import com.epam.estart.service.CommonService;
import java.util.Optional;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractService<I, D extends AbstractDTO<I>,
    E extends AbstractEntity<I>, R extends AbstractRepository<E, I>> implements CommonService<I, D, E> {

  protected final R repository;

  @Setter
  @Autowired
  protected ModelMapper modelMapper;

  @Autowired
  AbstractService(R repository) {
    this.repository = repository;
  }

  public abstract Class<E> getEntityClass();

  public abstract Class<D> getDTOClass();

  @Override
  public D create(D dto) {
    return modelMapper.map(repository.save(modelMapper.map(dto, getEntityClass())), getDTOClass());
  }

  @Override
  @Transactional(readOnly = true)
  public D getById(I id) {
    return getOptionalById(id).orElseThrow(
        () -> new IllegalArgumentException("Entity with id:" + id + " was not found."));
  }

  @Override
  public void update(D dto) {
    repository.save(modelMapper.map(dto, getEntityClass()));
  }

  @Override
  public void delete(D dto) {
    repository.delete(modelMapper.map(dto, getEntityClass()));
  }

  @Override
  public void deleteById(I id) {
    repository.deleteById(id);
  }

  @Override
  public Optional<D> getOptionalById(I id) {
    return repository.findById(id)
        .map(entity -> modelMapper.map(entity, getDTOClass()));
  }

  public boolean existsById(I id) {
    return repository.existsById(id);
  }
}
