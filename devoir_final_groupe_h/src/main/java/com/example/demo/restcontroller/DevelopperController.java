package com.example.demo.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.model.Ticket;
import com.example.demo.model.User;

import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;

@RestController
@RequestMapping("/rest_developpers")
public class DevelopperController {

	@Autowired
	private UserRepository developperRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	
	@GetMapping
	public List<User> get() {
		return developperRepository.devloppers();
	}
	
	@GetMapping("/{id}")
	public User get(@PathVariable Long id) {
		return developperRepository.devlopper(id);
	}

	
	@GetMapping("/{id}/tickets")
	public List<Ticket> tickets(@PathVariable Long id) {
		return developperRepository.devBugs(id);
	}
	
	@PostMapping("/{dev_id}/tickets/resolve/{t_id}")
	public void resloved(@PathVariable Long dev_id, @PathVariable Long t_id) {
		 User developper = developperRepository.devlopper(dev_id);
		 Ticket ticket = ticketRepository.getById(t_id);
		 
		 if(ticket.getDevelopper().getId() == developper.getId()) {
			 ticket.setStatus("résolu");
			 ticketRepository.save(ticket);
		 }
	}
	@PostMapping("/{dev_id}/tickets/unresolve/{t_id}")
	public void unresloved(@PathVariable Long dev_id, @PathVariable Long t_id) {
		 User developper = developperRepository.devlopper(dev_id);
		 Ticket ticket = ticketRepository.getById(t_id);
		 
		 if(ticket.getDevelopper().getId() == developper.getId()) {
			 ticket.setStatus("en cours de traitement");
			 ticketRepository.save(ticket);
		 }
	}
}
