package com.nistagram.authentication.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nistagram.authentication.service.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	List<User> findAll();

}
