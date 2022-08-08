package com.sreeny.todo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sreeny.todo.model.Todo;
import com.sreeny.todo.repository.TodoRepository;
import com.sreeny.todo.util.StatusEnum;

@Service
@Transactional
public class TodoService {
	
	protected static final Logger log = LoggerFactory.getLogger(TodoService.class);
	
	@Autowired
	public TodoRepository todoRepository;

	List<Todo> todoList = new ArrayList<Todo>();

	public TodoService() {
		
	}

	public List<Todo> getAllTodsByUserId(String userid) {
		List<Todo> list  = todoRepository.findAllByUserId(userid);
		return list;
	}
	
	public Todo getById(Long id) {
		Optional<Todo> todo = todoRepository.findById(id);
		return todo.get();
	}

	public Todo save(Todo todo) {
		log.info("Saving Todo :"+todo);
		
		if(StringUtils.isEmpty(todo.getStatus())) {
			todo.setStatus(StatusEnum.PENDING.name());
		}
		
		todo.setUserId("Sreeny");
		todoRepository.save(todo);
		return todo;
	}
	
	public Todo update(Todo todo, Long id) {
		log.info("Updating Todo :"+todo);
		Optional<Todo> entity = todoRepository.findById(id);
		if(!entity.isPresent() ) {
			throw new RuntimeException("Todo not found for id: "+id);
		}
		Todo todoNew = entity.get();
		if(todoNew != null) {
			todoNew.setTaskName(todo.getTaskName());
			todoNew.setDescription(todo.getDescription());
			todoNew.setDueDate(todo.getDueDate());
			todoNew.setStatus(todo.getStatus());
		}
		
		return todoRepository.save(todoNew);
	}
	
	public void remove(Long id) {
		log.info("Removing  Todo :"+id);
		todoRepository.deleteById(id);;
	}

}
