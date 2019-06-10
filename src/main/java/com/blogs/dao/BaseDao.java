package com.blogs.dao;

import com.blogs.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseDao<E extends AbstractEntity> extends JpaRepository<E, Long> {
    Optional<E> findOneById (Long id);
}
