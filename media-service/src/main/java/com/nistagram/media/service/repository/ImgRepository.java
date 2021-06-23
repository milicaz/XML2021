package com.nistagram.media.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.media.service.model.Images;

public interface ImgRepository extends JpaRepository<Images, Long> {

}
