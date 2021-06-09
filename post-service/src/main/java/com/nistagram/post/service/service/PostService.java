package com.nistagram.post.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nistagram.post.service.model.Post;
import com.nistagram.post.service.repository.PostRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public Post findOne(Long id) {
		Post post = postRepository.findOne(id);
		return post;
	}
	
	public List<Post> findAll(){
		return postRepository.findAll();
	}
	
	public List<Post> postsByUsername(String username){
		return postRepository.findByUsername(username);
	}
	
	public Post createPost(Post post) {
		return postRepository.save(post);
	}
	
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

}
