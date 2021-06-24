package com.nistagram.media.service.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.media.service.model.ImageModel;
import com.nistagram.media.service.model.Images;

public interface ImgRepository extends JpaRepository<Images, Long> {

//	Optional<Images> findByName(String name);
	Collection<Images> findByName(String name);
}
