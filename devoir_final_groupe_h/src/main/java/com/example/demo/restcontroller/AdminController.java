package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Ticket;
import com.example.demo.model.User;

import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;


@RestController
@RequestMapping("/rest_admins")
public class AdminController {
	
	@Autowired
	private UserRepository adminRepository;
	@Autowired
	private TicketRepository ticketRepository;
	
	
	@GetMapping
	public List<User> get() {
		return adminRepository.admins();
	}
	
	@GetMapping("/{id}")
	public User get(@PathVariable Long id) {
		return adminRepository.admin(id);
	}
	
	@GetMapping("/{id}/bugs")
	public List<Ticket> getBugs() {
		return adminRepository.bugsNotAssigned();
	}
	
	
	@PostMapping(("/{admin_id}/bugs/{bug_id}"))
	public void assign( @RequestBody User developper,
						@PathVariable(required = true) Long admin_id, 
						@PathVariable(required = true) Long bug_id ) {
		
		Ticket ticket = ticketRepository.getById(bug_id);
		User admin = adminRepository.admin(admin_id);
		ticket.setAdmin(admin);
		ticket.setDevelopper(developper);
		ticket.setStatus("en cours de traitement");
		ticketRepository.save(ticket);
	}

}
