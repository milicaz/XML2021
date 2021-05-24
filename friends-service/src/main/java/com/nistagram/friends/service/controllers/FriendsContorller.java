package com.nistagram.friends.service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friends")
public class FriendsContorller {

	@GetMapping("/status/check")
	public String status() {
		
		return "Friends Controller Working";
	}
}
