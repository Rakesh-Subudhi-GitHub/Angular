package com.exam.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtResponse {

	String token;  //create a token and pass it

	public JwtResponse() {
	}
	
	public JwtResponse(String token) {
		this.token = token;
	}
	
	
}//class
