package com.sreeny.todo.controller;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sreeny.todo.model.ToDo;

@Controller

public class TodoController {
	
	protected static final Logger log = LogManager.getLogger(TodoController.class);
	
	@RequestMapping (path = "/create",method = RequestMethod.GET)
	public String create(ModelMap model) {
		log.info("Creating new TODO!!");
		ToDo todo = new ToDo();
		model.addAttribute("newTodo", todo);
		return "createTodo";
		
	}
	
	@RequestMapping (path = "/save",method = RequestMethod.POST)
	public String save(@Valid ToDo todo, BindingResult result) {
		if(result.hasErrors()) {
			log.info("Found Validation Errors!!!");
			return "createTodo"; 
		}
		log.info(todo);
		return "index";
		
	}

}
