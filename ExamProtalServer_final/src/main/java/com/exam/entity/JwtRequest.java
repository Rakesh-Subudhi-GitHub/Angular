package com.exam.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest {

	String username;    //request username,password and create a token
	String password;
	
	public JwtRequest() {
	
	}
	
	public JwtRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}//constructor
	
}//class
