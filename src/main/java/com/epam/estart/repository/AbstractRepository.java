package com.epam.estart.repository;

import com.epam.estart.entity.AbstractEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface AbstractRepository<E extends AbstractEntity<I>, I> extends CrudRepository<E, I> {
}
