package com.sreeny.todo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sreeny.todo.model.Login;
import com.sreeny.todo.service.UserService;

@Controller
@RequestMapping(value="/")
public class IndexController {
	
	@Autowired
	UserService userService;
	
	protected static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value="/")
	public ModelAndView index(HttpServletResponse response) throws IOException{
		log.info("ToDo App called home page");
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/login")
	public String login(@Valid  Login login, BindingResult result, ModelMap model, HttpServletRequest request ) {
		
		if (result.hasErrors()) {
			log.info("Found Validation Errors!!!");
			return "index";
		}
		if(!userService.validateUser(login.getUserName())) {
			log.info("Invalid Username or password");
			return "index";
		}
		log.info("Login successfull ");
		request.getSession().setAttribute("message", "Welcome "+login.getUserName() +"!!!");
		return "createTodo";
	}
}
