package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entities.User;
import com.interfaces.IuserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = { "http://localhost:4200" })
public class UserController {

	@Autowired
	private IuserService service;
	
	@GetMapping("showAllUsers")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> list = service.getAllUsers();
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	

	@GetMapping("getUsersToAdd")
	public ResponseEntity<List<User>> getUsersToAdd(@RequestParam("pattern") String pattern) {
		List<User> list = service.getUsersToAdd(pattern);
		return new ResponseEntity<List<User>>(list, HttpStatus.OK);
	}
	
	@GetMapping("getUserById")
	public ResponseEntity<User> getUserById(@RequestParam("id") String userID) {
		User user = service.getUserById(Long.valueOf(userID));
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
}
