package com.ra.busBooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra.busBooking.DTO.UserLoginDTO;

import com.ra.busBooking.service.DefaultUserService;

@RestController
@RequestMapping("/login")
public class LoginController {
@Autowired
	private DefaultUserService userService;

	/*
	 * public LoginController(DefaultUserService userService) { super();
	 * this.userService = userService; }
	 */
    
	/*
	 * @ModelAttribute("user") public UserLoginDTO userLoginDTO() { return new
	 * UserLoginDTO(); }
	 * 
	 * @GetMapping public String login() { return "login"; }
	 */
	
	@PostMapping
	public ResponseEntity<Object> loginUser(@RequestBody
	UserLoginDTO userLoginDTO) {
		System.out.println("UserDTO"+userLoginDTO);
		UserDetails user=  userService.loadUserByUsername(userLoginDTO.getUsername());
		if(user != null) {
			return new ResponseEntity<Object>("User is successfully login with Id"+":"+user.getUsername(),HttpStatus.OK);
		}
		return new ResponseEntity<Object>("User not found with given credentials",HttpStatus.BAD_REQUEST);
	}
}
