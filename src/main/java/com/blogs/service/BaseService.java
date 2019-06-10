package com.blogs.service;

import com.blogs.entity.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface BaseService<T extends AbstractEntity> {

    long count ();

    void delete (T entity);

    List<T> findAll ();

    Optional<T> findById (Long id);

    T save (T entity);

}
