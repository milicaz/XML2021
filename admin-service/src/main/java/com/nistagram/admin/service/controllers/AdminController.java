package com.nistagram.admin.service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@GetMapping("/status/check")
	public String status() {
		
		return "Admin Controller Working";
	}

}
