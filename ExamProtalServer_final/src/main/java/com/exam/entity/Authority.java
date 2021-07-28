package com.exam.entity;

import org.springframework.security.core.GrantedAuthority;

public class Authority  implements GrantedAuthority{

	
	private String authority;
	
	public Authority(String authority) {  //pass role admin ,user then pass this 
		this.authority=authority;
		
	}//constructor
	
	@Override
	public String getAuthority() {
		
		return this.authority;
	}

}//class
