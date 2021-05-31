package com.nistagram.story.service.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/story")
public class StoryController {
	
	@GetMapping("/status/check")
	public String status() {
		
		return "Story Contorller Working";

	}
	
}
