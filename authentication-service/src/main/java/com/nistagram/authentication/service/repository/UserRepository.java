package com.nistagram.authentication.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.nistagram.authentication.service.model.User;

@CrossOrigin(origins = "http://localhost:4200")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findAll();
	
	List<User> findAllByUsername(String username);

}
