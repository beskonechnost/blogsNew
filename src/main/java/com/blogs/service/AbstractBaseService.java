package com.blogs.service;

import com.blogs.dao.BaseDao;
import com.blogs.entity.AbstractEntity;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@SuppressWarnings("unchecked")
@AllArgsConstructor
public abstract class AbstractBaseService<T extends AbstractEntity, E extends BaseDao> implements BaseService<T> {

    public final E dao;

    @Override
    public long count () {
        return dao.count();
    }

    @Override
    public void delete (T entity) {
        dao.delete(entity);
    }

    @Override
    public List<T> findAll () {
        return dao.findAll();
    }

    @Override
    public Optional<T> findById (Long id) {
        return dao.findOneById(id);
    }

    @Override
    public T save (T entity) {
        return (T) dao.save(entity);
    }

}
