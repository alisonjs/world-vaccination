package service.impl;

import domain.User;
import repository.UserRepository;
import service.UserService;

public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User getOne(Long id) {
        return repository.getOne(id);
    }

    @Override
    public User getAll() {
        return repository.getAll();
    }

    @Override
    public User login(User user) {
        return repository.getOne(user);
    }
}
