package com.nistagram.profile.service.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.profile.service.model.ProfileModel;

public interface ProfileModelRepository extends JpaRepository<ProfileModel, Long> {
	
	Collection<ProfileModel> findByUsername(String username);

}
