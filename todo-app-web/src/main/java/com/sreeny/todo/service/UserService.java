package com.sreeny.todo.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {
	
	public Boolean validateUser(String userName) {
		return !StringUtils.isEmpty(userName) && userName.equals("Sreeny");
	}

}
