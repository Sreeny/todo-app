package com.sreeny.todo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sreeny.todo.model.StatusResponse;
import com.sreeny.todo.model.Todo;
import com.sreeny.todo.service.TodoService;

@RestController
@Validated
@RequestMapping(path = "/api")
public class TodoController {

	@Autowired
	TodoService todoService;

	protected static final Logger log = LoggerFactory.getLogger(TodoController.class);

	@GetMapping(path = "/todo/{id}")
	public ResponseEntity<Todo> get(@PathVariable Long id) {
		log.info("Fetching Todo for the id: " + id);
		return new ResponseEntity<Todo>(todoService.getById(id), HttpStatus.OK);
	}

	@PostMapping(path = "/todo")
	public ResponseEntity<?> save(@Valid @RequestBody Todo todo) {

		Todo savedTodo = todoService.save(todo);

		StatusResponse response = new StatusResponse();
		response.setMessage("Created Todo with Id: " + todo.getId());
		response.setStatus("Success");
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@PostMapping(path = "/todo/update/{id}")
	public ResponseEntity<?> save(@Valid @RequestBody Todo todo, @PathVariable Long id) {

		todoService.update(todo, id);
		StatusResponse response = new StatusResponse();
		response.setMessage("Success");
		response.setStatus("Success");
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@RequestMapping(path = "/todo/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StatusResponse> delete(@PathVariable Long id) {
		log.info("Deleting for the id: " + id);
		todoService.remove(id);
		StatusResponse response = new StatusResponse();
		response.setMessage("Success");
		response.setStatus("Success");
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@RequestMapping(path = "/todo/allTodosByUserId/{userId}", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> allTodsByUserId(@PathVariable String userId) {
		log.info("Fetching Todos for the user: " + userId);
		return new ResponseEntity<List<Todo>>(todoService.getAllTodsByUserId(userId), HttpStatus.OK);
	}

	

}
