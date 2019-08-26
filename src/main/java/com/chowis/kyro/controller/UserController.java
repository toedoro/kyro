package com.chowis.kyro.controller;

import java.math.BigInteger;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chowis.kyro.message.KyroResponse;
import com.chowis.kyro.model.User;
import com.chowis.kyro.service.UserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/system/user", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE })
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<KyroResponse> create(@RequestBody User body) {
		try {
			User user = userService.create(body);

			String message = "User Succesfully created!";
			return ResponseEntity.status(HttpStatus.CREATED).body(KyroResponse.of(message).setSequence(user.getSequence()));
		} catch (Exception ex) {
			logger.debug(ex.getMessage(), ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(KyroResponse.of(ex.getMessage()));
		}
	}

	@GetMapping
	public ResponseEntity<Collection<User>> read() {
		Collection<User> users = userService.read();

		return ResponseEntity.status(HttpStatus.OK).body(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> read(@PathVariable BigInteger id) {
		User user = userService.read(id);

		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@PutMapping("/{id}")
	public ResponseEntity<KyroResponse> update(@PathVariable BigInteger id, @RequestBody User body) {
		try {
			body.setSequence(id);
			User user = userService.create(body);

			String message = "User Succesfully updated!";
			
			return ResponseEntity.status(HttpStatus.OK).body(KyroResponse.of(message).setSequence(user.getSequence()));
		} catch (Exception ex) {
			logger.debug(ex.getMessage(), ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(KyroResponse.of(ex.getMessage()));
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<KyroResponse> delete(@PathVariable BigInteger id) {
		try {
			userService.delete(id);
			String message = "User Succesfully deleted!";
			
			return ResponseEntity.status(HttpStatus.OK).body(KyroResponse.of(message));
		} catch (Exception ex) {
			String message = String.format("User not found %s", id);
			logger.debug(ex.getMessage(), ex);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(KyroResponse.of(message));
		}
	}
	
}
