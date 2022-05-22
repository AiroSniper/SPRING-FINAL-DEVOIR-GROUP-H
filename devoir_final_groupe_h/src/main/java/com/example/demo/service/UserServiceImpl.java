package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException("Nom d'utilisateur ou mot de passe erroné");
		for (Role r : user.getRoles())
			System.out.println("Role:" + r.getNom());
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.getRoles().stream().map(r -> new SimpleGrantedAuthority("ROLE_" + r.getNom()))
						.collect(Collectors.toList()));
	}

	@Override
	public void save(User user) {
		Role r = roleRepository.findByNom("USER");
		if (r == null)
			r = new Role("USER");
		user.setRoles(Arrays.asList(r));
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User oldUser = userRepository.findByUsername(user.getUsername());
		if(oldUser == null)
			userRepository.save(user);

	}

	@Override
	public List<User> liste() {
		return userRepository.findAll();

	}

	@Override
	public void supprimer(Long id) {
		userRepository.deleteById(id);

	}

	@Override
	public void ajouter(User user) {
		userRepository.save(user);
		SecurityContextHolder.getContext().getAuthentication();

	}

	@Override
	public void modifier(User user) {
		User u = getUser(user.getId());
		if (u != null) {
			u.setUsername(user.getUsername());
			userRepository.save(u);
		}

	}

	@Override
	public User getUser(Long id) {
		if (userRepository.existsById(id))
			return userRepository.findById(id).get();
		else

			return null;
	}

}
