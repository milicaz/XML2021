package com.nistagram.media.service.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.CompletionContext.Status;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.nistagram.media.service.model.ImageModel;
import com.nistagram.media.service.model.Images;
import com.nistagram.media.service.repository.ImageRepository;
import com.nistagram.media.service.repository.ImgRepository;

@CrossOrigin(origins = "http://localhost:4200")
@EnableWebMvc
@RestController
@RequestMapping(value = "/media")
public class MediaController {

//	@Autowired
//	ImageRepository imageRepository;
	
	@Autowired
	ImgRepository imgRepository;

	@GetMapping("/status/check")
	public String status() {

		return "Media Controller Working";
	}

	//consumes = "multipart/form-data"
//	@PostMapping(path = "/upload", consumes = "multipart/form-data")
//
//	public BodyBuilder uplaodImage(@RequestParam String imageFile, MultipartFile file) throws IOException {
//		System.out.println("Original Image Byte Size - " + file.getBytes().length);
//		ImageModel img = new ImageModel(file.getOriginalFilename(), file.getContentType(),
//				compressBytes(file.getBytes()));
//		imageRepository.save(img);
//		return ResponseEntity.status(HttpStatus.OK);
//	}

	@PostMapping(path = "/upload/{username}", consumes = "multipart/form-data")
	public ResponseEntity uploadImage(@RequestParam("imageFile") MultipartFile file,
			@PathVariable("username") String username) {
		try {
			String[] a = file.getOriginalFilename().split("\\.");

			System.out.println("Strin a " + a[0]);
			Images img = new Images(file.getBytes(), a[0], username);
			System.out.println("Poredjenje:  " + Arrays.equals(file.getBytes(), img.getPicByte()));
			// System.out.println("File get name" + file.getName());
//			Collection<Images> slike = imgRepository.findByPicByte(img.getPicByte());
			List<Images> slike = imgRepository.findAll();
			System.out.println("Slike su: " + slike);
			if (slike.isEmpty()) {
				imgRepository.save(img);
				return new ResponseEntity<>(img, HttpStatus.OK);
			} else {
				for (Images i : slike) {
					boolean b = Arrays.equals(i.getPicByte(), img.getPicByte());
					System.out.println("B je: " + b);
					if (b == true) {
						System.out.println("Slika vec postoji");
					} else {
						if(i.getUsername().equals(username)) {
							imgRepository.delete(i);
						}
						imgRepository.save(img);
						return new ResponseEntity<>(img, HttpStatus.OK);
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
//	@GetMapping(path ="/get/{imageName}")
//	public Images getImage(@PathVariable("imageName") String imageName) throws IOException {
//		System.out.println("Repo " + imgRepository.findByName(imageName));
//		Collection<Images> retreivedImage = imgRepository.findByName(imageName);
//		for(Images image : retreivedImage) {
//			if(image.getName().equals(imageName)) {
//				Images img = new Images(image.getPicByte(), image.getName());
//				System.out.println("Img je " + img);
//				return img;
//			}else {
//				System.out.println("Ne postoji slika sa tim imenom");
//			}
//		}
////		System.out.println("Name je " + retreivedImage.get().getName());
////		Images img = new Images();
//		return null;
//	}
	
	@GetMapping(path = "/get/image/{username}")
	public ResponseEntity<Images> getImg(@PathVariable("username") String username) {
		try {
			int secondsToSleep = 1;
			Thread.sleep(secondsToSleep * 60);
			System.out.println("Repo " + imgRepository.findAllByUsername(username));
			Collection<Images> retreivedImage = imgRepository.findAllByUsername(username);
			for (Images image : retreivedImage) {
				if (image.getUsername().equals(username)) {
					Images img = new Images(image.getPicByte(), image.getName(), image.getUsername());
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
	public ResponseEntity<Images> getImage(@PathVariable("imageName") String imageName) throws IOException {
		try {

			int secondsToSleep = 1;
			Thread.sleep(secondsToSleep * 60);
			System.out.println("Repo " + imgRepository.findByName(imageName));
			Collection<Images> retreivedImage = imgRepository.findByName(imageName);
			for (Images image : retreivedImage) {
				if (image.getName().equals(imageName)) {
					Images img = new Images(image.getPicByte(), image.getName(), image.getUsername());
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

	//		@GetMapping(path ="/get")
//		public ResponseEntity<Images> getImage(String imageName) throws IOException {
//			try {
//				
//			
//			System.out.println("Repo " + imgRepository.findByName(imageName));
//			Collection<Images> retreivedImage = imgRepository.findByName(imageName);
//			for(Images image : retreivedImage) {
//				if(image.getName().equals(imageName)) {
//					Images img = new Images(image.getPicByte(), image.getName());
//					System.out.println("Img je " + img);
//					return new ResponseEntity<>(img, HttpStatus.OK);
//				}else {
//					System.out.println("Ne postoji slika sa tim imenom");
//				}
//			}
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
////			System.out.println("Name je " + retreivedImage.get().getName());
////			Images img = new Images();
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
	
//	@GetMapping(path ="/get/{id}")
//	public ResponseEntity<Images> getImage(@PathVariable("id") Long id) throws IOException {
//		try {
//			final Optional<Images> retreivedImage = imgRepository.;
//			Ima
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//	}

//	@GetMapping(path = { "/get/{imageName}" })
//	public Images getImage(@PathVariable("imageName") String imageName) throws IOException {
//		final Optional<Images> retrievedImage = imgRepository.findByName(imageName);
//		Images img = new Images(retrievedImage.get().getPicByte(), retrievedImage.get().getName());
//		return img;
//	}

	// compress the image bytes before storing it in the database
	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setInput(data);
		deflater.finish();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
		return outputStream.toByteArray();
	}

	// uncompress the image bytes before returning it to the angular application
	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}

}
