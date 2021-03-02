package repository;

import domain.User;

public interface UserRepository {
    
    User save(User user);
    
    User getOne(Long id);

    User getAll();

    User getOne(User user);
}
