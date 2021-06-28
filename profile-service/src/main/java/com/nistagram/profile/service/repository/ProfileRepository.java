package com.nistagram.profile.service.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.profile.service.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
	
	Collection<Profile> findByUsername(String username);
}
