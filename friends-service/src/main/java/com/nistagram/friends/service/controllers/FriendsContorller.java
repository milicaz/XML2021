package com.nistagram.friends.service.controllers;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.CompletionContext.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nistagram.friends.service.model.Friends;
import com.nistagram.friends.service.repository.FriendsRepositry;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/friends")
public class FriendsContorller {
	
	@Autowired
	FriendsRepositry frepo;

	@GetMapping("/status/check")
	public String status() {
		
		return "Friends Controller Working";
	}
	
	@PostMapping(path = "/add", consumes = "application/json")
	public Status updateFriend(@Valid @RequestBody Friends friends) {
		frepo.save(friends);
		return Status.SUCCESS;
	}
	
	@GetMapping("/getFUsername/{username}")
	public ResponseEntity<?> getAllFriendsByUsername(@PathVariable("username") String username) {
		try {
			List<Friends> friends = frepo.findByFriendUname(username);
			if (friends.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(friends, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(path = "/update/{username}", consumes = "application/json")
	public Status updateFriends(@Valid @PathVariable String username, @RequestBody Friends friend) {
		frepo.save(friend);
		return Status.SUCCESS;
	}
	
	@GetMapping("/find/{username}")
	public ResponseEntity<Friends> getByUsername(@PathVariable("username") String username){
		try {
			Collection<Friends> f = frepo.findOneByUsername(username);
			for(Friends friend : f) {
				Friends fr = new Friends(friend.getId(), friend.getUsername(),
						friend.getFirstName(), friend.getLastName(), friend.getEmail(), friend.getDateOfBirth(), friend.getPhone(), 
						friend.getPrivacy(), friend.getPicByte(), friend.getFriendUname());
				return new ResponseEntity<>(fr, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
