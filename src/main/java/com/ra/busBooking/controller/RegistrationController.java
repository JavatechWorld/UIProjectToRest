package com.ra.busBooking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ra.busBooking.DTO.UserRegisteredDTO;
import com.ra.busBooking.model.User;
import com.ra.busBooking.service.DefaultUserService;

@RestController
@RequestMapping("/registration")
public class RegistrationController {

	 private DefaultUserService userService;

	    public RegistrationController(DefaultUserService userService) {
	        super();
	        this.userService = userService;
	    }

		/*
		 * @ModelAttribute("user") public UserRegisteredDTO userRegistrationDto() {
		 * return new UserRegisteredDTO(); }
		 * 
		 * @GetMapping public String showRegistrationForm() { return "register"; }
		 */

	    @PostMapping
	    public ResponseEntity<Object> registerUserAccount(@RequestBody
	              UserRegisteredDTO registrationDto) {
	        User user= userService.save(registrationDto);
	        if(user != null) {
	        	return new ResponseEntity<Object>("User is successfully registered",HttpStatus.OK);
	        }
	        return new ResponseEntity<Object>("User is was not  registered",HttpStatus.BAD_REQUEST);
	    }
}
