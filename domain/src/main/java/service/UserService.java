package service;

import domain.User;
import repository.UserRepository;

public interface UserService {

    /**
     * Create or update an user
     * @param user User
     * @return created or updated user
     */
    User save(User user);

    /**
     * Ger user by id
     * @param id User id
     * @return User found
     */
    User getOne(Long id);

    /**
     * Get all users
     * @return users
     */
    User getAll();

    /**
     * Log in
     * @param user User credentials
     * @return User logged
     */
    User login(User user);

}
