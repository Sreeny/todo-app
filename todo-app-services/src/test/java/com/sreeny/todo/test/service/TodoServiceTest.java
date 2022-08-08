package com.sreeny.todo.test.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sreeny.todo.model.Todo;
import com.sreeny.todo.repository.TodoRepository;
import com.sreeny.todo.service.TodoService;
import com.sreeny.todo.util.StatusEnum;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.testng.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertNotEquals;

@Test
public class TodoServiceTest extends AbstractTestNGSpringContextTests {

	@Mock
	TodoRepository todoRepository;

	@InjectMocks
	TodoService todoService;

	@BeforeMethod
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test_saveTodo_success() {
		Todo todo = new Todo();
		todo.setTaskName("my task1");
		todo.setDescription("my task1 description");
		todo.setDueDate(LocalDateTime.now());
		todo.setStatus(StatusEnum.PENDING.toString());

		when(todoRepository.save(any(Todo.class))).thenReturn(todo);

		Todo todoReturn = todoService.save(todo);
		assertEquals(todoReturn.getTaskName(), todo.getTaskName());

	}

	@Test
	public void test_saveTodo_status_pending_as_default_success() {
		Todo todo = new Todo();
		todo.setTaskName("my task1");
		todo.setDescription("my task1 description");
		todo.setDueDate(LocalDateTime.now());
		// todo.setStatus(StatusEnum.PENDING.toString());

		when(todoRepository.save(any(Todo.class))).thenReturn(todo);

		Todo todoReturn = todoService.save(todo);
		assertEquals(todoReturn.getStatus(), StatusEnum.PENDING.toString());

	}

	@Test(expectedExceptions = { RuntimeException.class })
	public void test_saveTodo_throw_runtime_exception_When_task_name_null() {

		Todo todo = new Todo();

		todo.setDescription("my task1 description");
		todo.setDueDate(LocalDateTime.now());
		todo.setStatus(StatusEnum.PENDING.toString());

		when(todoRepository.save(any(Todo.class))).thenThrow(RuntimeException.class);
		Todo todoReturn = todoService.save(todo);
	}

	@Test
	public void test_updateTodo_success_when_todo_exist() {
		Todo todo = new Todo();
		todo.setTaskName("my task New");
		todo.setDescription("my task New description");
		todo.setDueDate(LocalDateTime.now());
		todo.setStatus(StatusEnum.PENDING.toString());

		Long id = new Long(1);
		Todo todo1 = new Todo();
		todo1.setId(id);
		todo1.setTaskName("my task1");
		todo1.setDescription("my task1 description");
		todo1.setDueDate(LocalDateTime.now());
		todo1.setStatus(StatusEnum.PENDING.toString());

		when(todoRepository.save(any(Todo.class))).thenReturn(todo);
		when(todoRepository.findById(any(Long.class))).thenReturn(Optional.of(todo1));

		Todo todoReturn = todoService.update(todo, id);

		assertEquals(todoReturn.getTaskName(), todo.getTaskName());
		assertNotEquals(todoReturn.getTaskName(), "my task1");

	}

	@Test(expectedExceptions = { RuntimeException.class })
	public void test_updateTodo_throw_exception_when_todo_not_exist() {
		Todo todo = new Todo();
		todo.setTaskName("my task New");
		todo.setDescription("my task New description");
		todo.setDueDate(LocalDateTime.now());
		todo.setStatus(StatusEnum.PENDING.toString());

		Long id = new Long(1);
		Todo todo1 = new Todo();
		todo1.setId(id);
		todo1.setTaskName("my task1");
		todo1.setDescription("my task1 description");
		todo1.setDueDate(LocalDateTime.now());
		todo1.setStatus(StatusEnum.PENDING.toString());

		when(todoRepository.findById(any(Long.class))).thenReturn(Optional.empty());
		when(todoRepository.save(any(Todo.class))).thenThrow(RuntimeException.class);

		Todo todoReturn = todoService.update(todo, id);

	}

	@Test
	public void test_removeTodo_success() {

		Long id = new Long(1);
		ArgumentCaptor<Long> valueCapture = ArgumentCaptor.forClass(Long.class);
		doNothing().when(todoRepository).deleteById(valueCapture.capture());
		todoService.remove(id);
		verify(todoRepository, times(1)).deleteById(id);
		assertEquals(id, valueCapture.getValue());

	}
	
	@Test
	public void test_getById() {

		Long id = new Long(1);
		Todo todo1 = new Todo();
		todo1.setId(id);
		todo1.setTaskName("my task1");
		todo1.setDescription("my task1 description");
		todo1.setDueDate(LocalDateTime.now());
		todo1.setStatus(StatusEnum.PENDING.toString());
		
		when(todoRepository.findById(any(Long.class))).thenReturn(Optional.of(todo1));
		Todo retunTodo = todoService.getById(id);
		
		verify(todoRepository, times(1)).findById(id);
		
		assertEquals("my task1", retunTodo.getTaskName());

	}
	
	
	@Test
	public void test_getAllTodsByUserId_With_results() {

		List<Todo> todoList = new ArrayList<Todo>();
		
		String userId = "Sreeny";
		
		Long id = new Long(1);
		Todo todo1 = new Todo();
		todo1.setId(id);
		todo1.setTaskName("my task1");
		todo1.setDescription("my task1 description");
		todo1.setDueDate(LocalDateTime.now());
		todo1.setStatus(StatusEnum.PENDING.toString());
		todo1.setUserId(userId);
		
		Long id2 = new Long(2);
		Todo todo2 = new Todo();
		todo2.setId(id);
		todo2.setTaskName("my task2");
		todo2.setDescription("my task2 description");
		todo2.setDueDate(LocalDateTime.now());
		todo2.setStatus(StatusEnum.PENDING.toString());
		todo2.setUserId(userId);
		
		todoList.add(todo1);
		todoList.add(todo2);	
		
		when(todoRepository.findAllByUserId("Sreeny")).thenReturn(todoList);
		List<Todo> retunTodoList = todoService.getAllTodsByUserId("Sreeny");
		verify(todoRepository, times(1)).findAllByUserId(userId);
		assertEquals(retunTodoList.size(), todoList.size());
		

	}
	
	@Test
	public void test_getAllTodsByUserId_when_no_results() {

		List<Todo> todoList = new ArrayList<Todo>();
		
		String userId = "Sreeny";
		
		Long id = new Long(1);
		Todo todo1 = new Todo();
		todo1.setId(id);
		todo1.setTaskName("my task1");
		todo1.setDescription("my task1 description");
		todo1.setDueDate(LocalDateTime.now());
		todo1.setStatus(StatusEnum.PENDING.toString());
		todo1.setUserId(userId);
		
		Long id2 = new Long(2);
		Todo todo2 = new Todo();
		todo2.setId(id);
		todo2.setTaskName("my task2");
		todo2.setDescription("my task2 description");
		todo2.setDueDate(LocalDateTime.now());
		todo2.setStatus(StatusEnum.PENDING.toString());
		todo2.setUserId(userId);
		
		todoList.add(todo1);
		todoList.add(todo2);	
		
		when(todoRepository.findAllByUserId("")).thenReturn(todoList);
		List<Todo> retunTodoList = todoService.getAllTodsByUserId("Sreeny");
		verify(todoRepository, times(1)).findAllByUserId(userId);
		assertEquals(retunTodoList.size(), 0);
		

	}
	
	
	

}
 