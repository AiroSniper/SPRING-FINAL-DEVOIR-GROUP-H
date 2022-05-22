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

import com.example.demo.model.Ticket;
import com.example.demo.repository.TicketRepository;

@RestController
@RequestMapping("/rest_tickets")
public class TicketController {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	
	@PostMapping
	public void add(@RequestBody Ticket ticket) {
		ticketRepository.save(ticket);
	}
	
	@GetMapping
	public List<Ticket> get() {
		return ticketRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Ticket get(@PathVariable Long id) {
		return ticketRepository.getById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable(required = true) Long id) {
		Ticket ticket = ticketRepository.getById((id));
		ticketRepository.delete(ticket);
	}

}
