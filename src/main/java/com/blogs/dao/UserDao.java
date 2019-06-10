package com.blogs.dao;

import com.blogs.entity.User;

import java.util.Optional;

public interface UserDao extends BaseDao<User> {

    Optional<User> findOneByUsername (String username);

    User findByUsername (String username);

}
