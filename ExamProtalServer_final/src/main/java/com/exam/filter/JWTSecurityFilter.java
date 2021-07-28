package com.exam.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.service.UserSecurityServiceImpl;
import com.exam.utils.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JWTSecurityFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserSecurityServiceImpl userSecurityService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
										HttpServletResponse response, 
												FilterChain filterChain)throws ServletException, IOException 
	{
	
		//1.read the token from auth head
		final String requestTokenHeader=request.getHeader("Authorization");
		
		System.out.println("Token Header :: "+requestTokenHeader);
		
		String username=null;
		String jwtToken=null;
		
		if(requestTokenHeader !=null && requestTokenHeader.startsWith("Bearer ")) 
			
		//if(requestTokenHeader !=null )    //not required start with checking
		
		{
		
			//filter the token collect the JwtToken
			
			jwtToken=requestTokenHeader.substring(7); //user "Bearer " count is 7 (7-end) is token
			
		  try {
		  
			  username=jwtUtil.getUsername(jwtToken);//collect the username 
		  
		  }//try
		  catch(ExpiredJwtException je) {
			  System.out.println("JWTToken id get Expired..");
			  je.printStackTrace();
		  }
		  catch (Exception e) {
			  System.out.println("Some internal problem here in JwtFilter class...");
			e.printStackTrace();
	   	  }
		  
		  
		  //username should not be empty   or context-auth must be empty
		  if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null)
		  {
			  UserDetails userDetails = userSecurityService.loadUserByUsername(username);
			  
			  //all are perfect then comes to 
			 //validate the token
			 boolean isValid=jwtUtil.validateToken(jwtToken, userDetails.getUsername());
			 
			 
		 if(isValid)
		 {        
			 //then password to check user password or db password  check
			 
		    UsernamePasswordAuthenticationToken authToken= 
		    		                  new UsernamePasswordAuthenticationToken(username, 
		    		                       		                                userDetails.getPassword(),
		    		                       		                                    userDetails.getAuthorities());
		 
		    //web security pass request
		    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		    
		    //final object store the SecurityContext with UserDetails(username, password)
		    SecurityContextHolder.getContext().setAuthentication(authToken);
		    
		 }//3rd-if
			 
		}//2nd-if
		  
		  else {
			  System.out.println("Token is not valied....");
		  }
		  
	}//1st-if
		
		else {
			System.out.println("Token is Not start with Bearer ");
		}
	
		
		
		
	
		//all are done then user is valid or token also valid
		
		filterChain.doFilter(request, response);
		
		
	}//dofilter method

}//class
