package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/rest_users")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping
	public void add(@RequestBody User user) {
		userRepository.save(user);
	}
	
	@GetMapping
	public List<User> get() {
		return userRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public User get(@PathVariable Long id) {
		return userRepository.getById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable(required = true) Long id) {
		User user = userRepository.getById((id));
		userRepository.delete(user);
	}
}
