package com.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.entities.User;
import com.user.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	public UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers(){
		List<User> users = userService.getAll();
		if(users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
		return ResponseEntity.ok(users);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id){
		User user = userService.getUserById(id);
		if(user == null) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(user);
		}
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user){
		User newUser = userService.save(user);
		return ResponseEntity.ok(newUser);
	}
}
	
