package com.alisonjs.business.service;

import com.alisonjs.business.domain.User;
import com.alisonjs.business.exceptions.BusinessException;

import java.util.List;

/**
 * @author Alison Silva (alison.dev.silva@gmail.com)
 * @since 01/03/2021
 */
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
	List<User> getAll();

	/**
	 * Log in
	 * @param user User credentials
	 * @return User logged
	 */
	User login(String username, String password);

	void businessValidation(User user) throws BusinessException;

	void normalize(User user);

	User getByUsername(String username);

}
