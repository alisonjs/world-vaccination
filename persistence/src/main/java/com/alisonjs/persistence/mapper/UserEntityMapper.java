package com.alisonjs.persistence.mapper;

import com.alisonjs.business.domain.User;
import com.alisonjs.persistence.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserEntityMapper {

	UserEntity fromModel(User user);

	User toModel(UserEntity entity);

}
