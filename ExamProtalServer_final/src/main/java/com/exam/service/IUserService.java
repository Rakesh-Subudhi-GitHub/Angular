package com.exam.service;

import java.util.Set;

import com.exam.entity.User;
import com.exam.entity.UserRole;

public interface IUserService {

	//creating user
	public User createUser(User user,Set<UserRole> userRoles);//one user multiple roles thats way
	
	public boolean isUserNameExist(User user);
	
	public User getUserDetails(String name);
	
	public boolean deleteUser(String name);
	
	public Integer getUserId(String name);
	
}//interface
