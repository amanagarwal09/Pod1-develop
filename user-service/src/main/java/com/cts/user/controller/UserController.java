package com.cts.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.user.exception.UserNotFoundException;
import com.cts.user.model.User;
import com.cts.user.service.UserServiceImpl;

@CrossOrigin(origins = "${cross.origin.url}")
@RestController
public class UserController {

	@Autowired
	UserServiceImpl userService;
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user){
		ResponseEntity<User> response;
				try{
					response= userService.validateUser(user);
					}
				catch(UserNotFoundException ex) {
					return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
				}

				return response;
	}
	
}
