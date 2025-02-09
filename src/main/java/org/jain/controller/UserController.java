package org.jain.controller;

import java.util.List;

import org.jain.user.dto.UserRequest;
import org.jain.user.dto.UserResponse;
import org.jain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@GetMapping(path="/user/{id}", produces= {"application/json"})
	public UserResponse searchUser(@PathVariable int id)
	{
		return userService.searchUser(id);
	}
	
	
	@PostMapping(path="/user", produces= {"application/json"}, consumes= {"application/json"})
	public UserResponse saveUser(@RequestBody UserRequest user)
	{
		return userService.saveUser(user);
	}
	
	
	@GetMapping(path="/user", produces= {"application/json"})
	public List<UserResponse> getAllUsers()
	{
		return userService.getAllUsers();
	}
}
