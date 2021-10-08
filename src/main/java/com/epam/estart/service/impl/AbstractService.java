package com.epam.estart.service.impl;


import com.epam.estart.entity.AbstractEntity;
import com.epam.estart.repository.AbstractRepository;
import com.epam.estart.service.CommonService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractService<I,
    E extends AbstractEntity<I>, R extends AbstractRepository<E, I>> implements CommonService<I, E> {

  protected final R repository;

  @Autowired
  AbstractService(R repository) {
    this.repository = repository;
  }

  public abstract Class<E> getEntityClass();

  @Override
  public E create(E entity) {
    return repository.save(entity);
  }

  @Override
  @Transactional(readOnly = true)
  public E getById(I id) {
    return getOptionalById(id).orElseThrow(
        () -> new IllegalArgumentException("Entity with id:" + id + " was not found."));
  }

  @Override
  public void update(E entity) {
    repository.save(entity);
  }

  @Override
  public void delete(E entity) {
    repository.delete(entity);
  }

  @Override
  public void deleteById(I id) {
    repository.deleteById(id);
  }

  @Override
  public Optional<E> getOptionalById(I id) {
    return Optional.ofNullable(repository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Entity with id:" + id + " was not found.")));
  }

  public boolean existsById(I id) {
    return repository.existsById(id);
  }
}
