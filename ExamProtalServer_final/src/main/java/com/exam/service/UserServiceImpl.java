package com.exam.service;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	//repository
	private UserRepository userRepo;
	private RoleRepository roleRepo;
	
	//constructor injection
	@Autowired
	public UserServiceImpl(UserRepository userRepo,RoleRepository roleRepo) {
		this.userRepo=userRepo;
		this.roleRepo=roleRepo;
	}//constructor
	
	
	@Autowired
	private BCryptPasswordEncoder passowrdEncoder;
	
	
	
	@Override
	public Integer getUserId(String name) {
		
		Integer byId = userRepo.findByUsername(name).getUserid();
		return byId;
	}//method
	
	
	//fast check user exist or not
	@Override
	public boolean isUserNameExist(User user) {
		
		Example<User> example= Example.of(user);
		
		return userRepo.exists(example);
	}//method
	
	//creating user
	@Override
	public User createUser(User user, Set<UserRole> userRoles) {
		
		user.setEnable(true);
		user.setPassword(passowrdEncoder.encode(user.getPassword()));
		
		//save the role 
		for(UserRole ur:userRoles) //collect the role to given  all are collect and finally
		{
			roleRepo.save(ur.getRole());
		}//for
		
		user.getUserRole().addAll(userRoles);//save the all role
		
		//finally user data is simple save it
		User usersave = userRepo.save(user);
		
		return usersave;
		
	}//method


	//find user details
	@Override
	public User getUserDetails(String name) {
		
		User findByUsername = userRepo.findByUsername(name);
		
		Optional<User> findById = userRepo.findById(findByUsername.getUserid());
		
		if(findById.isPresent())
			{
				return findById.get();
			}
		
		return null;
		
	}//method

	@Override
	public boolean deleteUser(String name) {
		
		User findByUsername = userRepo.findByUsername(name);
		
		int id=findByUsername.getUserid();
		
		boolean existsById = userRepo.existsById(id);
		
		if(existsById)
			{
				userRepo.deleteById(id);
				return true;
			}
		
		return false;
	
	}//method
	

}//class
