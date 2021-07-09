package com.nistagram.story.service.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nistagram.story.service.model.Story;
import com.nistagram.story.service.repository.StoryRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/story")
public class StoryController {

	private byte[] bytes;

	@Autowired
	StoryRepository storyRepository;

	@GetMapping("/status/check")
	public String status() {

		return "Story Contorller Working";

	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllStories() {
		List<Story> stories = storyRepository.findAll();
		return new ResponseEntity<>(stories, HttpStatus.OK);
	}

	@GetMapping("/getUsername/{username}")
	public ResponseEntity<?> getAllStoriesByUsername(@PathVariable("username") String username) {
		try {
			List<Story> stories = storyRepository.findByUsername(username);
			if (stories.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getStory/{id}")
	public ResponseEntity<?> getStory(@PathVariable("id") Long id) {
		Optional<Story> storyData = storyRepository.findById(id);

		if (storyData.isPresent()) {
			return new ResponseEntity<>(storyData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/upload")
	public void uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
		this.bytes = file.getBytes();
	}

	@PostMapping("/create")
	public ResponseEntity<Story> createStory(@RequestBody Story story) {
		try {
			story.setPicByte(this.bytes);
			Story _story = storyRepository.save(story);
			return new ResponseEntity<>(_story, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
