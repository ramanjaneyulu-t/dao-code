package com.taodigital.productservice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.taodigital.productservice.dto.ErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ErrorResponse handleException(Exception ex) {
		ErrorResponse response = new ErrorResponse();
		response.setMessage(ex.getMessage());
		return response;
	}
}
