package com.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.entity.User;
import com.exam.repo.UserRepository;

@Service
public class UserSecurityServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepo.findByUsername(username);
		
		if(user == null)
		{
			System.out.println("User Not found ..");
			throw new UsernameNotFoundException("USER NOT FOUND/Invalied Credentials");
		}
		
		
		return user;
		
	}//method

	
}//class
