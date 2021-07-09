package com.nistagram.profile.service.contorllers;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nistagram.profile.service.model.Profile;
import com.nistagram.profile.service.model.ProfileModel;
import com.nistagram.profile.service.repository.ProfileModelRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/profile/model")
public class ProfileModelController {
	
	private byte[] bytes;
	
	@Autowired
	ProfileModelRepository pmrepo;
	
	@GetMapping("/status/check")
	public String status() {
		return "Profile Model Controller Working";
	}
	
	@GetMapping("get/profiles")
	public List<ProfileModel> getProfiles() {
		return pmrepo.findAll();
	}
	
	@GetMapping("get/profile/{username}")
	
	
	public ResponseEntity<ProfileModel> getProfile(@PathVariable("username") String username){
		try {
//			int secondsToSleep = 1;
//			Thread.sleep(secondsToSleep * 120);
			Collection<ProfileModel> profiles = pmrepo.findByUsername(username);
			for(ProfileModel p : profiles) {
				if(p.getUsername().equals(username)){
					ProfileModel prof = new ProfileModel(p.getId(), p.getUsername(), p.getFirstName(),
							p.getLastName(), p.getEmail(), p.getDateOfBirth(), p.getPhone(), p.getPrivacy(), p.getPicByte());
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
	
//	@PutMapping("update/profile/{username}")
//	public ResponseEntity<ProfileModel> updateProfile(@PathVariable String username, @RequestBody ProfileModel profile) {
//		ProfileModel profileUpdate = pmrepo.save(profile);
//		return new ResponseEntity<>(profileUpdate, HttpStatus.OK);
//	}
	
//	@PutMapping("/upload")
//	public void updatePicture(@RequestBody ProfileModel profile) throws IOException {
//		profile.setPicByte(this.bytes);
//		pmrepo.save(profile);
//		this.bytes = null;
//	}
	
	@PostMapping("/upload")
	public void uploadImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		this.bytes = file.getBytes();
	}

	//Umesto PostMapping("/add")
	@PutMapping("/update/{username}")
	public void updateProfile(@PathVariable String username, @RequestBody ProfileModel profile) throws IOException, InterruptedException {
		int secondsToSleep = 1;
		Thread.sleep(secondsToSleep * 120);
		profile.setPicByte(this.bytes);
		pmrepo.save(profile);
		this.bytes = null;
	}
	
	@PostMapping(path = "/add", consumes = "application/json" )
	public Status updateProfile(@Valid @RequestBody ProfileModel profile) {
		
		pmrepo.save(profile);
		return Status.SUCCESS;
		
	}
	
	

}
