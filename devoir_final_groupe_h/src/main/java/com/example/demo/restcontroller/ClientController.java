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
@RequestMapping("/rest_clients")
public class ClientController {

	@Autowired
	private UserRepository clientRepository;
	
	
	@GetMapping
	public List<User> get() {
		return clientRepository.clients();
	}
	
	@GetMapping("/{id}")
	public User get(@PathVariable Long id) {
		return clientRepository.client(id);
	}
	
	@GetMapping("/{id}/tickets")
	public List<Ticket> tickets(@PathVariable Long id) {
		return clientRepository.clientBugs(id);
	}
	
	@Autowired
	private TicketRepository ticketRepository;
	
	
	@PostMapping("/{id}/tickets")
	public void add(@RequestBody Ticket ticket, @PathVariable Long id) {
		User client = clientRepository.client(id);
		ticket.setClient(client);
		ticketRepository.save(ticket);
	}
}
