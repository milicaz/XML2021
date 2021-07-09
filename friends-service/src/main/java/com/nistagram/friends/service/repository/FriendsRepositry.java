package com.nistagram.friends.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nistagram.friends.service.model.Friends;

public interface FriendsRepositry extends JpaRepository<Friends, Long> {

}
