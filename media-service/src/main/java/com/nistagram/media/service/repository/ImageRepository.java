package com.nistagram.media.service.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.media.service.model.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
	
	Collection<ImageModel> findByName(String name);
	
	Collection<ImageModel> findAllByUsername(String username);
	
	Collection<ImageModel> findByPicByte(byte[] picByte);
	

}
