package com.exam.control;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.JwtRequest;
import com.exam.entity.JwtResponse;
import com.exam.entity.User;
import com.exam.service.UserSecurityServiceImpl;
import com.exam.utils.JwtUtil;

@RestController
@CrossOrigin("*")
public class AuthenticateController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserSecurityServiceImpl userSecurityService; 
	
	@Autowired
	private UserDetailsService userService;
	
	//generate Token
	@PostMapping("/generate-Token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception{
		
		try {
			authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());//private method is call and check the user id and password
		
		}//try
		
		catch (UsernameNotFoundException ce) {
			throw new Exception("User Not Found Exception...."+ce.getMessage());
		}//catch
	
		//authenticated the user then generate the Token
		
		UserDetails userDetails = userSecurityService.loadUserByUsername(jwtRequest.getUsername());
		
		String token = jwtUtil.generateToken(userDetails.getUsername());
		
		return ResponseEntity.ok(new JwtResponse(token));
		
	}//method
	
	
	
	private void authenticate(String username,String password) throws Exception {
	
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
         	}//try
	
	catch (DisabledException de) {
					System.out.println("User is Disabled..");
					throw new Exception("User is Disabled"+de.getMessage());
	}//catch
	
	catch (BadCredentialsException be) {
				System.out.println("Invalied Creadentials...");
				throw new Exception("Invalied Credntials.."+be.getMessage());
	}
	
	catch (Exception e) {
			System.out.println("Some internal problem");
			throw new Exception("Some internal problem !! "+e.getMessage()); 
	}
	
		
	}//method

	@GetMapping("/currentUser")
	public User getCurrentUser(Principal principal)
	{
		return (User) userSecurityService.loadUserByUsername(principal.getName());
		 
	}
}//class
