package com.sreeny.todo.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sreeny.todo.controller.TodoController;
import com.sreeny.todo.model.ToDo;

@Service
public class TodoService {
	
	protected static final Logger log = LoggerFactory.getLogger(TodoService.class);

	List<ToDo> todoList = new ArrayList<ToDo>();

	public TodoService() {
		ToDo todo = new ToDo();
		todo.setId(12244L);
		todo.setTaskName("Complete Clearance Paperwork");
		todo.setDueDate(LocalDateTime.now());
		todo.setDescription("Complete Clearance Paperwork");
		todo.setStatus("Complete");

		ToDo todo1 = new ToDo();
		todo1.setId(12244L);
		todo1.setTaskName("Complete Training ");
		todo1.setDescription("Complete Training ");
		todo1.setDueDate(LocalDateTime.now());
		todo1.setStatus("Pending");
		todoList.addAll(Arrays.asList(todo, todo1));
	}

	public List<ToDo> getAllTodsByUserId(String userid) {
		return todoList;
	}
	
	public ToDo getById(Long id) {
		Optional<ToDo> todo = this.todoList.stream().filter(x-> x.getId().longValue() == id.longValue()).findAny();
		if(todo.isPresent()) {
			return todo.get();
		}
		return null;
	}

	public void save(ToDo todo) {
		log.info("Saving Todo :"+todo);
		todo.setId(Long.valueOf(todoList.size() + 1));
		todo.setStatus("Pending");
		this.todoList.add(todo);
	}
	
	public void remove(Long id) {
		log.info("Removing  Todo :"+id);
		Optional<ToDo> todo = this.todoList.stream().filter(x-> x.getId().longValue() == id.longValue()).findAny();
		if(todo.isPresent()) {
			this.todoList.remove(todo.get());
		}
	}

}
