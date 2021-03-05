package com.alisonjs.business.repository;

import com.alisonjs.business.domain.User;

import java.util.List;

/**
 * @author Alison Silva (alison.dev.silva@gmail.com)
 * @since 01/03/2021
 */
public interface UserRepository {
    
    User save(User user);
    
    User getOne(Long id);

    List<User> getAll();

    User getByUsernameAndPassword(String username, String password);
}
