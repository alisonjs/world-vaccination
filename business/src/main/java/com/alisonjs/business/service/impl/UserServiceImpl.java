package com.alisonjs.business.service.impl;

import com.alisonjs.business.domain.User;
import com.alisonjs.business.exceptions.AuthorizationException;
import com.alisonjs.business.exceptions.BusinessException;
import com.alisonjs.business.exceptions.NotFoundException;
import com.alisonjs.business.repository.UserRepository;
import com.alisonjs.business.service.UserService;

import java.util.List;

/**
 * @author Alison Silva (alison.dev.silva@gmail.com)
 * @since 01/03/2021
 */
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) throws BusinessException {
        businessValidation(user);
        return repository.save(user);
    }

    @Override
    public User getOne(Long id) {
        User user = repository.getOne(id);
        if(user == null) {
            throw new NotFoundException("User not found with the given id  " + id);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User login(String username, String password) {
        User userLogged = repository.getByUsernameAndPassword(username, password);
        if(userLogged == null){
            throw new AuthorizationException("User or password incorrect.");
        }
        return userLogged;
    }

    @Override
    public void businessValidation(User user) throws BusinessException {
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new BusinessException("Username required");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new BusinessException("Password required");
        }

        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new BusinessException("Email required");
        }
    }
}
