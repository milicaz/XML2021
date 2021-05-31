package com.nistagram.post.service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post")
public class PostController {
	
	@GetMapping("/status/check")
	public String status() {
		
		return "Post Controller Working";
	}

}
