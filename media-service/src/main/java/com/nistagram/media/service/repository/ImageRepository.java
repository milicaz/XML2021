package com.nistagram.media.service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.media.service.model.ImageModel;

public interface ImageRepository extends JpaRepository<ImageModel, Long> {
	
	Optional<ImageModel> findByName(String name);

}
