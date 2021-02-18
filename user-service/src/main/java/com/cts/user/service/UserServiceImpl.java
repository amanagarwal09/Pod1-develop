package com.cts.user.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.user.exception.UserNotFoundException;
import com.cts.user.model.User;
import com.cts.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	UserRepository userRepository;

	//Validate User Credentials
	@Override
	public ResponseEntity<User> validateUser(User user) {
		
		Optional<User> userOp=userRepository.findByUsername(user.getUsername());

		if(!userOp.isEmpty() && userOp.get().getPassword().equals(user.getPassword())) {
				userOp.get().setPassword("");
				return new ResponseEntity<>(userOp.get(),HttpStatus.OK);
				
		}
		//User Not Found
		throw new UserNotFoundException();
		
		
	}

	
	
}
