package com.sreeny.todo.test.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.LocalDateTime;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.sreeny.todo.model.Todo;
import com.sreeny.todo.service.UserService;
import com.sreeny.todo.util.StatusEnum;

@Test
public class UserServiceTest extends AbstractTestNGSpringContextTests {
	
	@InjectMocks
	UserService userService;

	@BeforeMethod
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test_validateUser_success() {
		
		Boolean result = userService.validateUser("Sreeny");

		assertTrue(result);
		
	}

}
