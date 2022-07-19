package com.sreeny.todo.controller;

import java.io.IOException;


import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/")
public class IndexController {
	protected static final Logger log = LogManager.getLogger(IndexController.class);

	@RequestMapping(value="/")
	public ModelAndView index(HttpServletResponse response) throws IOException{
		log.info("ToDo App called home page");
		log.debug("ToDo App called home page");
		log.error("ToDo App called home page");
		log.trace("ToDo App called home page");
		return new ModelAndView("index");
	}
}
