package com.cts.user.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.cts.user.exception.UserNotFoundException;
import com.cts.user.model.User;
import com.cts.user.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserServiceImpl userService;

	//Valid Credentials
	@Test
	void validUserTest() {

		User user = new User("admin", "123", true);
		when(userService.validateUser(user)).thenReturn(new ResponseEntity<User>(user, HttpStatus.OK));

		ResponseEntity<User> responseEntity = userController.login(user);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

	//Invalid Credentials
	@Test
	void unauthorizedUserTest() {

		User user = new User("admin", "123", true);
		when(userService.validateUser(user)).thenReturn(new ResponseEntity<User>(user, HttpStatus.UNAUTHORIZED));

		ResponseEntity<User> responseEntity = userController.login(user);

		assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
	}
	
	@Test
	void invalidUserTest() {

		User user = new User("admin", "123", true);
		when(userService.validateUser(user)).thenThrow(new UserNotFoundException());
		
		ResponseEntity<User> responseEntity = userController.login(user);
		
		assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());

	}

}
