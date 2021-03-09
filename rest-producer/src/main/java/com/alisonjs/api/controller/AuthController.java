package com.alisonjs.api.controller;

import com.alisonjs.api.dto.UserLoginDto;
import com.alisonjs.api.dto.UserTokenDto;
import com.alisonjs.api.dto.mapper.UserDtoMapper;
import com.alisonjs.api.dto.mapper.UserTokenDtoMapper;
import com.alisonjs.business.domain.User;
import com.alisonjs.business.service.UserService;
import com.alisonjs.security.authentication.UserAuthenticationManager;
import com.alisonjs.security.provider.UserToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UserService userService;

    private final UserDtoMapper mapper;

    private final UserTokenDtoMapper tokenDtoMapper;

    private final UserAuthenticationManager userAuthenticationManager;

    public AuthController(UserService userService, UserDtoMapper mapper, UserTokenDtoMapper tokenDtoMapper, UserAuthenticationManager userAuthenticationManager) {
        this.userService = userService;
        this.mapper = mapper;
        this.tokenDtoMapper = tokenDtoMapper;
        this.userAuthenticationManager = userAuthenticationManager;
    }

    @PostMapping("/signin")
    public ResponseEntity<UserTokenDto> login(@RequestBody UserLoginDto userDto) {
        UserToken userToken = userAuthenticationManager.auth(mapper.formUserLoginDto(userDto));
        User user = userService.login(userDto.getUsername(), userDto.getPassword());
        UserTokenDto userTokenDto = tokenDtoMapper.fromModel(userToken);
        userTokenDto.setUser(mapper.fromModel(user));
        return ResponseEntity.ok(userTokenDto);
    }

}
