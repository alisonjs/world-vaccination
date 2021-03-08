package com.alisonjs.persistence.repository;

import com.alisonjs.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {

	Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    UserEntity findUserEntityByUsername(String username);
}
