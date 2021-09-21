package br.com.carv.finances.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.carv.finances.dto.UserDto;
import br.com.carv.finances.entities.User;
import br.com.carv.finances.exceptions.AuthenticationException;
import br.com.carv.finances.exceptions.BusinessRuleException;
import br.com.carv.finances.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	
	private final UserService userService;
	
	public UserController(UserService userService) {
		
		this.userService = userService;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/messageTest")
	public String testMessage() {
		return "Hi SpringBoot";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveUser")
	public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
		
		User user = new User(); 
		user.setNameUser(userDto.getNameUser());
		user.setEmailUser(userDto.getEmailUser());
		user.setPasswordUser(userDto.getPasswordUser());
		
		try {
			User userResult = userService.saveUser(user);
			return new ResponseEntity<User>(userResult, HttpStatus.CREATED);
		} catch (BusinessRuleException err) {
			return ResponseEntity.badRequest().body(err.getMessage());
		}
		
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/authUser")
	public ResponseEntity<?> authUser(@RequestBody UserDto userDto) {
		
		try {
			User user = userService.authUser(userDto.getEmailUser(), userDto.getPasswordUser());
			return ResponseEntity.ok(user);
		} catch (AuthenticationException err) {
			return ResponseEntity.badRequest().body(err.getMessage());
		}
		
		
	}
	

}
