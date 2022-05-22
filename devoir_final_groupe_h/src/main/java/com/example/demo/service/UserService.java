package com.example.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.model.User;

public interface UserService extends UserDetailsService {

	void save(User user);

	List<User> liste();

	void supprimer(Long id);

	void ajouter(User user);

	void modifier(User user);

	User getUser(Long id);
}
