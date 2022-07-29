package com.sreeny.todo.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.sreeny.todo.model.Todo;
import com.sreeny.todo.util.StatusEnum;

@Service
public class TodoService {
	
	private static String TODO_SERVICES_ENDPOINT = "http://localhost:8090/todo-app-services/api/todo";
	
	protected static final Logger log = LoggerFactory.getLogger(TodoService.class);
	
	@Autowired
	public RestTemplate restTemplate;
	
	List<Todo> todoList = new ArrayList<Todo>();

	public TodoService() {
		
	}

	public List<Todo> getAllTodsByUserId(String userid) {
		ResponseEntity<Todo[]> response =  null;
		response = restTemplate.getForEntity(TODO_SERVICES_ENDPOINT+"/allTodosByUserId/Sreeny",Todo[].class);
		log.info("Response received from server: "+response.getBody().toString());
		List<Todo> list  = Arrays.asList(response.getBody());
		return list;
	}
	
	public Todo getById(Long id) {
		ResponseEntity<Todo> response =  null;
		response = restTemplate.getForEntity(TODO_SERVICES_ENDPOINT+"/"+id,Todo.class);
		log.info("Response received from server: "+response.getBody().toString());
		return response.getBody();
	}

	public void save(Todo todo) {
		log.info("Saving Todo :"+todo);
		
		if(StringUtils.isEmpty(todo.getStatus())) {
			todo.setStatus(StatusEnum.PENDING.name());
		}
		todo.setUserId("Sreeny");
		HttpEntity<Todo> request = new HttpEntity<>(todo);
		ResponseEntity<Todo> response = restTemplate.exchange(TODO_SERVICES_ENDPOINT+"/", HttpMethod.POST, request, Todo.class);
		
	}
	
	public void update(Todo todo) {
		log.info("Updating Todo :"+todo);
		
		if(StringUtils.isEmpty(todo.getStatus())) {
			todo.setStatus(StatusEnum.PENDING.name());
		}
		HttpEntity<Todo> request = new HttpEntity<>(todo);
		ResponseEntity<Todo> response = restTemplate.exchange(TODO_SERVICES_ENDPOINT+"/update/"+todo.getId(), HttpMethod.POST, request, Todo.class);
	}
	
	public void remove(Long id) {
		log.info("Removing  Todo :"+id);
		restTemplate.delete(TODO_SERVICES_ENDPOINT+"/delete/"+id);
	}

}
