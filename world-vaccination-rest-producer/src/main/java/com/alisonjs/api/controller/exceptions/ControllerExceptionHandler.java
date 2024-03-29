package com.alisonjs.api.controller.exceptions;

import com.alisonjs.business.domain.ResponseMessage;
import com.alisonjs.business.exceptions.AuthorizationException;
import com.alisonjs.business.exceptions.BusinessException;
import com.alisonjs.business.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.Date;

@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ApiError> handlerNotFoundException(NotFoundException e) {
		ApiError error = ApiError.builder().code(HttpStatus.NOT_FOUND.value()).msg(e.getMessage()).date(new Date())
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler
	public ResponseEntity<ApiError> handlerBusinessException(AuthorizationException e) {
		ApiError error = ApiError.builder().code(HttpStatus.UNAUTHORIZED.value()).msg(e.getMessage()).date(new Date())
				.build();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}

	@ExceptionHandler
	public ResponseEntity<ApiError> handlerBusinessException(BusinessException e) {
		ApiError error = ApiError.builder().code(HttpStatus.BAD_REQUEST.value()).msg(e.getMessage()).date(new Date())
				.build();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}

	@ExceptionHandler
	public ResponseEntity<ApiError> handlerBusinessException(BadCredentialsException e) {
		ApiError error = ApiError.builder().code(HttpStatus.UNAUTHORIZED.value()).msg(e.getMessage()).date(new Date())
				.build();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
		return ResponseEntity
				.status(HttpStatus.EXPECTATION_FAILED)
				.body(new ResponseMessage("The file is too large!"));
	}

}
