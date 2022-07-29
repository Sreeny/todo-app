package com.sreeny.todo.exceptions;

import java.util.ArrayList;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.sreeny.todo.model.StatusResponse;

@RestControllerAdvice
public class TodoExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StatusResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
		ArrayList<String> errors = new ArrayList<String>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String errorMessage = error.getDefaultMessage();
			errors.add(errorMessage);
		});
		StatusResponse response = new StatusResponse();
		response.setMessage(errors.toString());
		response.setStatus("Failed");
		return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler({ EmptyResultDataAccessException.class, RuntimeException.class })
	public ResponseEntity<StatusResponse> handleEmptyResultDataAccessExceptions(RuntimeException ex) {
		StatusResponse response = new StatusResponse();
		response.setMessage(ex.getMessage());
		response.setStatus("Failed");
		return new ResponseEntity(response, HttpStatus.NOT_FOUND);
	}
}
