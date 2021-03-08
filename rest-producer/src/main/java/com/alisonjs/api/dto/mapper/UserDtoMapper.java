package com.alisonjs.api.dto.mapper;

import com.alisonjs.api.dto.UserDto;
import com.alisonjs.business.domain.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

@Mapper(builder = @Builder(disableBuilder = true))
public interface UserDtoMapper {

	UserDto fromModel(User user);

	User toModel(UserDto user);

}
