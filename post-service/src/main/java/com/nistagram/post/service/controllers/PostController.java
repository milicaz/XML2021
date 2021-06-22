package com.nistagram.post.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nistagram.post.service.model.Post;
import com.nistagram.post.service.service.PostService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping("/status/check")
	public String status() {
		
		return "Post Controller Working";
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getAllPosts(){
		List<Post> posts = postService.findAll();
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<?> getPost(@PathVariable Long postId){
		return new ResponseEntity<>(postService.findOne(postId), HttpStatus.OK);
	}

}
