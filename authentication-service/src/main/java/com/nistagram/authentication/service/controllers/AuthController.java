package com.nistagram.authentication.service.controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.CompletionContext.Status;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/user/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
		try {
			List<User> users = urepo.findAllByUsername(username);
			for(User u : users) {
				if(u.getUsername().equals(username)){
					return new ResponseEntity<>(u, HttpStatus.OK);
			}else {
				System.out.println("User ne postoji.");
			}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
	
	@PutMapping(path = "update/user/{username}", consumes = "application/json")
	public ResponseEntity<User> updateUser(@PathVariable String username, @RequestBody User user){
		User userUpdate = urepo.save(user);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
	
	

}
