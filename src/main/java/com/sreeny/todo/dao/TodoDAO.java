package com.sreeny.todo.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sreeny.todo.model.Todo;

@Component
public interface TodoDAO {
	
	public List<Todo> getAllTodsByUserId(String userId);
	public Todo saveTodo(Todo todo);
	public Todo get(Long id);
	public Long remove(Long id);

}
