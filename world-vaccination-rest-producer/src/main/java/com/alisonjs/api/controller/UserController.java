package com.alisonjs.api.controller;

import com.alisonjs.api.dto.PasswordDto;
import com.alisonjs.api.dto.UserDto;
import com.alisonjs.api.dto.UserLoginDto;
import com.alisonjs.api.dto.UserTokenDto;
import com.alisonjs.api.dto.mapper.UserDtoMapper;
import com.alisonjs.api.dto.mapper.UserTokenDtoMapper;
import com.alisonjs.business.domain.User;
import com.alisonjs.business.service.UserService;
import com.alisonjs.security.authentication.UserAuthenticationManager;
import com.alisonjs.security.provider.UserToken;
import com.fasterxml.jackson.databind.node.TextNode;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

	private final UserService userService;

	private final UserDtoMapper mapper;

	public UserController(UserService userService, UserDtoMapper mapper) {
		this.userService = userService;
		this.mapper = mapper;
	}

	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Saved user"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 401, message = "Unauthorized"),
	})
	@PostMapping
	public ResponseEntity<UserDto> save(@RequestBody UserDto userDto) {
		User createdUser = userService.save(mapper.toModel(userDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(mapper.fromModel(createdUser));
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Updated user"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized - Invalid token jwt"),
			@ApiResponse(code = 404, message = "User not found with the given id")
	})
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> update(@PathVariable(name = "id") Long id, @RequestBody UserDto userDto) {
		User userdb = userService.getOne(id);
		User tosave = mapper.toModel(userDto);
		tosave.setVersion(userdb.getVersion());
		tosave.setId(id);
		return ResponseEntity.ok(mapper.fromModel(userService.save(tosave)));
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User"),
			@ApiResponse(code = 404, message = "User not found with the given id")
	})
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getById(@PathVariable(name = "id") Long id) {
		User user = userService.getOne(id);
		return ResponseEntity.ok(mapper.fromModel(user));
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Users list")
	})
	@GetMapping
	public ResponseEntity<List<UserDto>> listAll() {
		List<UserDto> users = new ArrayList<>();
		userService.getAll().forEach(user -> users.add(mapper.fromModel(user)));
		return ResponseEntity.ok(users);
	}

	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Updated user"),
			@ApiResponse(code = 400, message = "Bad request"),
			@ApiResponse(code = 401, message = "Unauthorized - Invalid token jwt"),
			@ApiResponse(code = 404, message = "User not found with the given id")
	})
	@PutMapping("/{id}/password")
	public ResponseEntity<UserDto> updatePassword(@PathVariable(name = "id") Long id, @RequestBody PasswordDto password) {
		User user = userService.getOne(id);
		user.setPassword(password.getPassword());
		User savedUser = userService.save(user);
		return ResponseEntity.ok(mapper.fromModel(savedUser));
	}

}
