package com.nistagram.profile.service.contorllers;

import java.util.Collection;

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

import com.nistagram.profile.service.model.Profile;
import com.nistagram.profile.service.repository.ProfileRepository;

import javassist.compiler.ast.Variable;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	ProfileRepository prepo;
	
	@GetMapping("/status/check")
	public String status() {
		
		return "Profile Contorller Working";
	}
	
	@PostMapping(path = "/update", consumes = "application/json" )
	public Status updateProfile(@Valid @RequestBody Profile profile) {
		
		prepo.save(profile);
		return Status.SUCCESS;
		
	}
	
	@GetMapping("get/profile/{username}")
	public ResponseEntity<Profile> getProfile(@PathVariable("username") String username){
		try {
			Collection<Profile> profiles = prepo.findByUsername(username);
			for(Profile p : profiles) {
				if(p.getUsername().equals(username)){
					Profile prof = new Profile(p.getId(), p.getUsername(), p.getFirstName(),
							p.getLastName(), p.getEmail(), p.getDateOfBirth(), p.getPhone(), p.getPrivacy());
					return new ResponseEntity<>(prof, HttpStatus.OK);
				}else {
					System.out.println("Ne postoji taj profil");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("update/profile/{username}")
	public ResponseEntity<Profile> updateProfile(@PathVariable String username, @RequestBody Profile profile){
		Profile profileUpdate = prepo.save(profile);
		return new ResponseEntity<>(profile, HttpStatus.OK);
		
	}
}
