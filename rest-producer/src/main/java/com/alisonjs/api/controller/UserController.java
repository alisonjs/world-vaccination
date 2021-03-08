package com.alisonjs.api.controller;

import com.alisonjs.api.dto.UserDto;
import com.alisonjs.api.dto.UserLoginDto;
import com.alisonjs.api.dto.UserTokenDto;
import com.alisonjs.api.dto.mapper.UserDtoMapper;
import com.alisonjs.api.dto.mapper.UserTokenDtoMapper;
import com.alisonjs.business.domain.User;
import com.alisonjs.business.service.UserService;
import com.alisonjs.security.authentication.UserAuthenticationManager;
import com.alisonjs.security.provider.UserToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

	private final UserService userService;

	private final UserDtoMapper mapper;

	private final UserTokenDtoMapper tokenDtoMapper;

	private final UserAuthenticationManager userAuthenticationManager;

	public UserController(UserService userService, UserDtoMapper mapper, UserTokenDtoMapper tokenDtoMapper, UserAuthenticationManager userAuthenticationManager) {
		this.userService = userService;
		this.mapper = mapper;
		this.tokenDtoMapper = tokenDtoMapper;
		this.userAuthenticationManager = userAuthenticationManager;
	}

	@PostMapping
	public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
		User createdUser = userService.save(mapper.toModel(userDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.fromModel(createdUser));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDto> update(@PathVariable(name = "id") Long id, @RequestBody UserDto user) {
		user.setId(id);
		User updatedUser = userService.save(mapper.toModel(user));
		return ResponseEntity.ok(mapper.fromModel(updatedUser));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable(name = "id") Long id) {
		User user = userService.getOne(id);
		return ResponseEntity.ok(mapper.fromModel(user));
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> listAll() {
		List<UserDto> users = new ArrayList<>();
		userService.getAll().forEach(user -> users.add(mapper.fromModel(user)));
		return ResponseEntity.ok(users);
	}

	@PostMapping("/login")
	public ResponseEntity<UserTokenDto> login(@RequestBody UserLoginDto user) {
		UserToken userToken = userAuthenticationManager.auth(mapper.formUserLoginDto(user));
		return ResponseEntity.ok(tokenDtoMapper.fromModel(userToken));
	}

	@PutMapping("/{id}/password")
	public ResponseEntity<UserDto> login(@PathVariable(name = "id") Long id, @RequestBody String password) {
		User user = userService.getOne(id);
		user.setPassword(password);
		User savedUser = userService.save(user);
		return ResponseEntity.ok(mapper.fromModel(savedUser));
	}

}
