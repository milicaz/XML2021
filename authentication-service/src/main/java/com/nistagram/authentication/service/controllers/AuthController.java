package com.nistagram.authentication.service.controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nistagram.authentication.service.model.User;
import com.nistagram.authentication.service.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	UserRepository urepo;
	
	@GetMapping("/status/check")
	public String status() {
		
		return "Auth Controller Working";
	}
	
	@GetMapping("/all/users")
	public Collection<User> getUsers() {
		return urepo.findAll();
		
	}

}
