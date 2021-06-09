package com.nistagram.post.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nistagram.post.service.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	List<Post> findAll();
	Post findOne(Long id);	
	List<Post> findByUsername(String username);
}
