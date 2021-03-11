package com.alisonjs.api.dto.mapper;

import com.alisonjs.api.dto.UserTokenDto;
import com.alisonjs.security.provider.UserToken;
import org.mapstruct.Mapper;

@Mapper
public interface UserTokenDtoMapper {

	UserTokenDto fromModel(UserToken user);

}
