package com.alisonjs.persistence.repository.impl;

import com.alisonjs.business.domain.User;
import com.alisonjs.persistence.mapper.UserEntityMapper;
import com.alisonjs.persistence.repository.UserJpaRepository;
import com.alisonjs.business.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alison Silva (alison.dev.silva@gmail.com)
 * @since 01/03/2021
 */
@Component
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository repository;

    private final UserEntityMapper mapper;

    public UserRepositoryImpl(UserJpaRepository repository, UserEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public User save(User user) {
        return mapper.toModel(repository.save(mapper.fromModel(user)));
    }

    @Override
    public User getOne(Long id) {
        return repository.findById(id).map(mapper::toModel).orElse(null);
    }

    @Override
    public List<User> getAll() {

        List<User> results = new ArrayList<>();
        repository.findAll().forEach(userEntity -> results.add(mapper.toModel(userEntity)));
        return results;
    }

    @Override
    public User getByUsernameAndPassword(String username, String password) {
        return repository.findByUsernameAndPassword(username, password).map(mapper::toModel).orElse(null);
    }
}
