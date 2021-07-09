package com.nistagram.post.service.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nistagram.post.service.model.Post;
import com.nistagram.post.service.repository.PostRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/post")
public class PostController {
	
	private byte[] bytes;

	@Autowired
	PostRepository postRepostitory;

	@GetMapping("/status/check")
	public String status() {

		return "Post Controller Working";
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllPosts() {
		List<Post> posts = postRepostitory.findAll();
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}

	@GetMapping("/getUsername/{username}")
	public ResponseEntity<?> getAllPostsByUsername(@PathVariable("username") String username) {
		try {
			List<Post> posts = postRepostitory.findByUsername(username);
			if (posts.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(posts, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/get/{postId}")
	public ResponseEntity<?> getPost(@PathVariable("postId") Long postId) {
		Optional<Post> postData = postRepostitory.findById(postId);

		if (postData.isPresent()) {
			return new ResponseEntity<>(postData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/upload")
	public void uploadImage(@RequestParam("image") MultipartFile file) throws IOException{
		this.bytes = file.getBytes();
	}

	@PostMapping("/create")
	public ResponseEntity<?> createPost(@RequestBody Post post) {
		try {
			post.setPicByte(this.bytes);
			Post _post = postRepostitory.save(post);
			return new ResponseEntity<>(_post, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Post> updatePost(@PathVariable("id") Long id, @RequestBody Post post) {
		Optional<Post> postData = postRepostitory.findById(id);
		
		if(postData.isPresent()) {
			Post _post = postData.get();
			_post.setCaption(post.getCaption());
			return new ResponseEntity<Post>(postRepostitory.save(_post), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deletePost(@PathVariable("id") Long id) {
		try {
			postRepostitory.deleteById(id);
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@DeleteMapping("/all")
	public ResponseEntity<HttpStatus> deleteAllPosts() {
		try {
			postRepostitory.deleteAll();
			return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
