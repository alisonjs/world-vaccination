package com.alisonjs.api.controller;

import com.alisonjs.api.dto.UserLoginDto;
import com.alisonjs.api.dto.UserTokenDto;
import com.alisonjs.api.dto.mapper.UserDtoMapper;
import com.alisonjs.api.dto.mapper.UserTokenDtoMapper;
import com.alisonjs.business.domain.User;
import com.alisonjs.business.service.UserService;
import com.alisonjs.security.authentication.UserAuthenticationManager;
import com.alisonjs.security.provider.UserToken;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/auth", produces = "application/json")
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

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Signed user and access token"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 401, message = "Bad credentials")
    })
    @PostMapping("/signin")
    public ResponseEntity<UserTokenDto> signin(@RequestBody UserLoginDto userDto) {
        UserToken userToken = userAuthenticationManager.auth(mapper.formUserLoginDto(userDto));
        User user = userService.login(userDto.getUsername(), userDto.getPassword());
        UserTokenDto userTokenDto = tokenDtoMapper.fromModel(userToken);
        userTokenDto.setUser(mapper.fromModel(user));
        return ResponseEntity.ok(userTokenDto);
    }

}
