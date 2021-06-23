package com.nistagram.authentication.service.controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.CompletionContext.Status;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nistagram.authentication.service.model.User;
import com.nistagram.authentication.service.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200")
@EnableWebMvc
@Configuration
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
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
	
	@PostMapping(path = "/user/register", consumes = "application/json" )
	public Status registerUser(@Valid @RequestBody User newUser) {
		List<User> users = urepo.findAll();
		for(User user : users) {
			System.out.println("Registered user: " + newUser.toString());
			if (user.getUsername().equals(newUser.getUsername())) {
                System.out.println("User with this Username Already exists!");
                return Status.FAILED;
            }
        }
        urepo.save(newUser);
		return Status.SUCCESS;
	}
	

}
