package com.sreeny.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sreeny.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long>{
	
  public List<Todo> findAllByUserId(String userId);

}
