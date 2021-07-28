package com.exam.control;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.entity.Role;
import com.exam.entity.User;
import com.exam.entity.UserRole;
import com.exam.service.IUserService;
import com.rk.hepler.HelperMessage;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {
	
	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome to my new project ";
	}

	@Autowired
	private IUserService userService;
	
	//Constructor injection
	public UserController(IUserService userService) {
	
		this.userService=userService;
	
	}//constructor
	
	
	//create User
	@PostMapping("/")
	public User createUser(@RequestBody User user)throws Exception
	{
		HelperMessage help=new HelperMessage();
		
		//fast check
		if(userService.isUserNameExist(user)) {
			help.setMsg("User already exsit try another one .. !!");
			 throw new Exception("UserName Alredy exsit try another ... !!");
		}
		
		boolean flag=false;
		
		//1st USER table set that comes client side(user)
		
		//set 2nd table ROLE Table
		Role role=new Role();
		role.setRoleName("USER");
		
		
		//3rd table is user
		UserRole userRole=new UserRole();
		userRole.setUser(user);
		userRole.setRole(role);
		
		//final add the list
		Set<UserRole> roles=new HashSet<UserRole>();
		roles.add(userRole);
		
		//save the user data
		return userService.createUser(user, roles);
		
		/*
		 * flag=true;
		 * 
		 * if(flag) { help.setMsg("User data is saved"); return new
		 * ResponseEntity<HelperMessage>(help,HttpStatus.ACCEPTED);
		 * 
		 * } else { help.setMsg("some internal problem here data is not saved .. !!");
		 * return new ResponseEntity<HelperMessage>(help,HttpStatus.BAD_REQUEST); }
		 */
		
	}//method
	
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) 
	{
		User userDetails = userService.getUserDetails(username);
		
		return userDetails;
		
	}//method
	
	@DeleteMapping("/{username}")
	public String getDeleteUser(@PathVariable("username") String username)
	{
		
		Integer userId = userService.getUserId(username);
		
		boolean deleteUser = userService.deleteUser(username);
		if(userId !=0) {
		if(deleteUser)
		{
			return username+" User Data delete Successfully .. !!";
		}
		else
		{
			return "Some Internal problem here..So try Again ..!!";
		}
		}
		else {
			return username+" userDetails is not found try another one ..!!";
		}
	}//method
	
}//class
