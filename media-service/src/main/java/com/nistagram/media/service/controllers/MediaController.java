package com.nistagram.media.service.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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

	@PostMapping(path = "/upload", consumes = "multipart/form-data")
	public ResponseEntity uploadImage(@RequestParam("imageFile") MultipartFile file) {
		try {
			Images img = new Images(file.getBytes(), file.getOriginalFilename());
			imgRepository.save(img);
			return new ResponseEntity<>(img, HttpStatus.OK);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(path ="/get/{imageName}")
	public Images getImage(@PathVariable("imageName") String imageName) throws IOException {
		System.out.println("Repo " + imgRepository.findByName(imageName));
		final Optional<Images> retreivedImage = imgRepository.findByName(imageName);
		System.out.println("Name je " + retreivedImage.get().getName());
		Images img = new Images(retreivedImage.get().getPicByte(), retreivedImage.get().getName());
		return img;
		
	}

//	@GetMapping(path = { "/get/{imageName}" })
//	public ImageModel getImage(@PathVariable("imageName") String imageName) throws IOException {
//		final Optional<ImageModel> retrievedImage = imageRepository.findByName(imageName);
//		ImageModel img = new ImageModel(retrievedImage.get().getName(), retrievedImage.get().getType(),
//				decompressBytes(retrievedImage.get().getPicByte()));
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
