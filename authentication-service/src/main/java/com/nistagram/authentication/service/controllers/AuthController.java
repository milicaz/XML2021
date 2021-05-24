package com.nistagram.authentication.service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@GetMapping("/status/check")
	public String status() {
		
		return "Auth Controller Working";
	}

}
