package com.nistagram.profile.service.contorllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@GetMapping("/status/check")
	public String status() {
		
		return "Profile Contorller Working";
	}

}
