package com.sreeny.todo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sreeny.todo.model.Login;
import com.sreeny.todo.model.StatusResponse;
import com.sreeny.todo.service.UserService;
import com.sreeny.todo.util.StatusEnum;

@Controller
@RequestMapping(path = "/api")
public class IndexController {
	
	@Autowired
	UserService userService;
	
	protected static final Logger log = LoggerFactory.getLogger(IndexController.class);

	@RequestMapping(value="/")
	public ModelAndView index(HttpServletResponse response) throws IOException{
		log.info("ToDo App called home page");
		return new ModelAndView("index");
	}
	
	@PostMapping(value="/login")
	public ResponseEntity<?> login(@RequestBody Login login, BindingResult result, ModelMap model, HttpServletRequest request ) {
		model.addAttribute("statusEnum",StatusEnum.values());
		StatusResponse response = new StatusResponse();
		if (result.hasErrors()) {
			log.info("Found Validation Errors!!!");
			response.setStatus("Failed");
			return new ResponseEntity(response, HttpStatus.OK);
		}
	
		if(!userService.validateUser(login.getUserName())) {
			model.addAttribute("invalidLoginMessage", "Invalid Username or password");
			log.info("Invalid Username or password");
			response.setStatus("Failed");
			return new ResponseEntity(response, HttpStatus.OK);
		}
		log.info("Login successfull ");
		response.setStatus("Success");
		request.getSession().setAttribute("message", "Welcome "+login.getUserName() +"!!!");
		return new ResponseEntity(response, HttpStatus.OK);
		
	}
}
