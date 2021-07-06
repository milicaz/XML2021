package com.nistagram.media.service.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nistagram.media.service.model.ImageModel;
import com.nistagram.media.service.model.Images;
import com.nistagram.media.service.repository.ImageRepository;

@CrossOrigin(origins = "http://localhost:4200")
@EnableWebMvc
@RestController
@RequestMapping(value = "/image")
public class ImageController {

	@Autowired
	ImageRepository irepo;
	
	@PostMapping(path = "/upload/{username}", consumes = "multipart/form-data")
	public ResponseEntity uploadImage(@RequestParam("imageFile") MultipartFile file,
			@PathVariable("username") String username) {
		try {
			String[] a = file.getOriginalFilename().split("\\.");

			System.out.println("Strin a " + a[0]);
			ImageModel image = new ImageModel(a[0], file.getBytes(), username);
			System.out.println("Poredjenje:  " + Arrays.equals(file.getBytes(), image.getPicByte()));
			// System.out.println("File get name" + file.getName());
//			Collection<Images> slike = imgRepository.findByPicByte(img.getPicByte());
			List<ImageModel> slike = irepo.findAll();
			System.out.println("Slike su: " + slike);
			if (slike.isEmpty()) {
				irepo.save(image);
				return new ResponseEntity<>(image, HttpStatus.OK);
			} else {
				for (ImageModel im : slike) {
					boolean b = Arrays.equals(im.getPicByte(), image.getPicByte());
					System.out.println("B je: " + b);
					if (b == true) {
						System.out.println("Slika vec postoji");
					} else {
						irepo.save(image);
						return new ResponseEntity<>(image, HttpStatus.OK);
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path = "/get/image/{username}")
	public ResponseEntity<ImageModel> getImg(@PathVariable("username") String username) {
		try {
			int secondsToSleep = 1;
			Thread.sleep(secondsToSleep * 60);
			System.out.println("Repo " + irepo.findAllByUsername(username));
			Collection<ImageModel> retreivedImage = irepo.findAllByUsername(username);
			for (ImageModel image : retreivedImage) {
				if (image.getUsername().equals(username)) {
					ImageModel img = new ImageModel(image.getName(), image.getPicByte(), image.getUsername());
					System.out.println("Img je " + img);
					return new ResponseEntity<>(img, HttpStatus.OK);
				} else {
					System.out.println("Ne postoji slika sa tim imenom");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
//			System.out.println("Name je " + retreivedImage.get().getName());
//			Images img = new Images();
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// Ova radi
	@GetMapping(path = "/get/{imageName}")
	public ResponseEntity<ImageModel> getImage(@PathVariable("imageName") String imageName) throws IOException {
		try {

			int secondsToSleep = 1;
			Thread.sleep(secondsToSleep * 60);
			System.out.println("Repo " + irepo.findByName(imageName));
			Collection<ImageModel> retreivedImage = irepo.findByName(imageName);
			for (ImageModel image : retreivedImage) {
				if (image.getName().equals(imageName)) {
					ImageModel img = new ImageModel(image.getName(), image.getPicByte(), image.getUsername());
					System.out.println("Img je " + img);
					return new ResponseEntity<>(img, HttpStatus.OK);
				} else {
					System.out.println("Ne postoji slika sa tim imenom");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
//		System.out.println("Name je " + retreivedImage.get().getName());
//		Images img = new Images();
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
