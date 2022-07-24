package com.sreeny.todo.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sreeny.todo.controller.TodoController;
import com.sreeny.todo.dao.TodoDAO;
import com.sreeny.todo.model.Todo;

@Service
@Transactional
public class TodoService {
	
	protected static final Logger log = LoggerFactory.getLogger(TodoService.class);
	
	@Autowired
	@Qualifier("todoDao")
	public TodoDAO todoDao;

	List<Todo> todoList = new ArrayList<Todo>();

	public TodoService() {
		
	}

	public List<Todo> getAllTodsByUserId(String userid) {
		List<Todo> list  = todoDao.getAllTodsByUserId(userid);
		return list;
	}
	
	public Todo getById(Long id) {
		Todo todo = todoDao.get(id);
		return todo;
	}

	public void save(Todo todo) {
		log.info("Saving Todo :"+todo);
		todo.setStatus("Pending");
		todo.setUserId("Sreeny");
		todoDao.saveTodo(todo);
	}
	
	public void remove(Long id) {
		log.info("Removing  Todo :"+id);
		Optional<Todo> todo = this.todoList.stream().filter(x-> x.getId().longValue() == id.longValue()).findAny();
		if(todo.isPresent()) {
			this.todoList.remove(todo.get());
		}
	}

}
