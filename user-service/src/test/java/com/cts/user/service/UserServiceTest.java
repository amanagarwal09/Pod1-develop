package com.cts.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.user.exception.UserNotFoundException;
import com.cts.user.model.User;
import com.cts.user.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;

	// User Not Found
	@Test
	void validateUserTestInvalidCred() {

		User user = new User("admin", "123", true);
		when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.empty());
		try {
			userService.validateUser(user);
		} catch (Exception ex) {
			assertThat(ex).isInstanceOf(UserNotFoundException.class);
		}

	}

	//User Found with given credentials
	@Test
	void validateUserTestValidCred() {

		User user = new User("admin", "123", true);

		when(userRepository.findByUsername(user.getUsername())).thenReturn(Optional.of(user));

		ResponseEntity<User> responseEntity = userService.validateUser(user);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

}
