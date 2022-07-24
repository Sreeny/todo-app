package com.sreeny.todo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sreeny.todo.model.StatusResponse;
import com.sreeny.todo.model.Todo;
import com.sreeny.todo.service.TodoService;

@Controller

public class TodoController {

	@Autowired
	TodoService todoService;

	protected static final Logger log = LoggerFactory.getLogger(TodoController.class);

	@RequestMapping(path = "/create", method = RequestMethod.GET)
	public String create(ModelMap model) {
		log.info("Creating new TODO!!");
		Todo todo = new Todo();
		model.addAttribute("newTodo", todo);
		return "createTodo";
	}

	@RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable Long id, ModelMap model) {

		log.info("Fetching ToDo for update for :" + id);
		Todo todo = todoService.getById(id);
		model.addAttribute("toDo", todo);
		return "createTodo";
	}

	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public String save(@Valid Todo todo, BindingResult result) {
		if (result.hasErrors()) {
			log.info("Found Validation Errors!!!");
			return "createTodo";
		}
		log.info("Save/Update :"+todo.toString());
		todoService.save(todo);
		return "createTodo";
	}

	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		log.info("Deleting for the id: " + id);
		todoService.remove(id);
		StatusResponse response  = new StatusResponse();
		response.setMessage("Success");
		response.setStatus("Success");
		return new ResponseEntity(response, HttpStatus.OK);
	}

	@RequestMapping(path = "/allTodos", method = RequestMethod.GET)
	public String allTods() {
		log.info("Invoking All todos");
		return "myTodoList";
	}

	@RequestMapping(path = "/allTodosByUserId/{userId}", method = RequestMethod.GET)
	public ResponseEntity<List<Todo>> allTodsByUserId(@PathVariable String userId) {
		log.info("Fetching Todos for the user: " + userId);
		return new ResponseEntity<List<Todo>>(todoService.getAllTodsByUserId(userId), HttpStatus.OK);
	}

}
