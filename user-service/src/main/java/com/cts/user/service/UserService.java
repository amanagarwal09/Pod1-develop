package com.cts.user.service;

import org.springframework.http.ResponseEntity;

import com.cts.user.model.User;


public interface UserService {

	ResponseEntity<User> validateUser(User user);
	
}
