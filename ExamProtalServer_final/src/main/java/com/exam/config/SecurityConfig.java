package com.exam.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.exam.filter.JWTSecurityFilter;
import com.exam.service.UserSecurityServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserSecurityServiceImpl userSecurityService;  
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private InvalidUserAuthEntryPoint authEndPoint;
	
	@Autowired
	private JWTSecurityFilter jwtFilter;
	
//-------------------- config authentication manager ------------------------------------	
	
	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		
		return super.authenticationManager();
	}
	
	
//-------------------------- config with AuthenticationManager--------------------------------------	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			
		//verify the username or password 
		auth.userDetailsService(userSecurityService)
			.passwordEncoder(passwordEncoder); 
		
	}//auth method
	
//------------------------------------------ config with http urls----------------------------------------------------	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers("/generate-Token","/user/","/h2-console/*","/swagger-ui.html").permitAll() //in this url is permit all bcz this Two are 1st is save username password 
			.antMatchers(HttpMethod.OPTIONS).permitAll()
			
			
			.anyRequest().authenticated() //any request come then login requried or JWT token
			
			.and()   
			
			//Error page 
			.exceptionHandling()
			.authenticationEntryPoint(authEndPoint)
			
			.and()	
			
			//session 
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			
			// verify user for 2nd Request on words..
			.and()
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			
			;//end
		
	}//http method
	
}//class
