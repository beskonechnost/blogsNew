package com.blogs.service;

import com.blogs.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService extends BaseService<User>, UserDetailsService {

    Optional<User> findOneById (Long id);

    Optional<User> findOneByUsername (String username);

    User getCurrentUser ();

    User getCurrentUser (boolean isNullable);

    List<User> findAll (String sortBy, Boolean asc);

}
