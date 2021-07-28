package com.exam.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.entity.User;

public interface UserRepository extends JpaRepository<User,Serializable> {
	
	public User findByUsername(String username);

}
