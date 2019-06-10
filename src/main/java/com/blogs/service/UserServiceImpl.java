package com.blogs.service;

import com.blogs.dao.UserDao;
import com.blogs.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends AbstractBaseService<User, UserDao> implements UserService {

    public UserServiceImpl (UserDao dao) {
        super(dao);
    }

    @Override
    public Optional<User> findOneById(Long id) {
        return dao.findOneById(id);
    }

    @Override
    public Optional<User> findOneByUsername(String username) {
        return dao.findOneByUsername(username);
    }

    @Override
    public User getCurrentUser() {
        return getCurrentUser(false);
    }

    @Override
    public User getCurrentUser(boolean isNullable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication != null && authentication.isAuthenticated()) {
            return getUserFromDb(((UserDetails) authentication.getPrincipal()).getUsername(), isNullable);
        } else {
            if(!isNullable) {
                throw new AuthenticationServiceException("User is not authenticated");
            }
            return null;
        }
    }

    @Override
    public List<User> findAll(String sortBy, Boolean asc) {
        return dao.findAll(getSort(asc, sortBy));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = dao.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }

        return user;
    }

    private Sort getSort (Boolean asc, String sortBy) {
        Sort.Direction direction = Boolean.TRUE.equals(asc) ? Sort.Direction.ASC : Sort.Direction.DESC;
        return new Sort(direction, sortBy);
    }

    private User getUserFromDb (String username, boolean isNullable) {
        User user = dao.findByUsername(username);

        if(user == null && !isNullable) {
            throw new UsernameNotFoundException("No user found with username: " + username);
        }
        return user;
    }
}
