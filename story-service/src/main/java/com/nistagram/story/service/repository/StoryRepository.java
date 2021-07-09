package com.nistagram.story.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nistagram.story.service.model.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
	
	List<Story> findByUsername(String username);

}
