package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interfaces.IuserService;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = { "http://localhost:4200" })
public class UserController {

	@Autowired
	private IuserService service;
}
