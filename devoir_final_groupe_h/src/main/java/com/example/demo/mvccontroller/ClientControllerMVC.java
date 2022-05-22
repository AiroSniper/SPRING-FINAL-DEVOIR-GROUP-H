package com.example.demo.mvccontroller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Ticket;
import com.example.demo.model.User;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;


@Controller
@RequestMapping("/client")
public class ClientControllerMVC {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private UserService userService;
	
	
	
	
	@GetMapping("/tickets")
	public String clientBugs(Model m) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Username "+auth.getName());
		m.addAttribute("bugs", userRepository.clientBugs(userRepository.findByUsername(auth.getName()).getId()));
		return "client/tickets";	
	}
	
	@GetMapping("/create_ticket")
	public String create(Model m) {
		m.addAttribute("ticket", new Ticket());
		return "client/create";	
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model m, @PathVariable long id) {
	    Ticket t =	ticketRepository.getById(id);
	    m.addAttribute("ticket", t);
		return "client/update";	
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute ("ticket") Ticket ticket) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = userRepository.findByUsername(auth.getName());
		ticket.setClient(u);
		ticket.setStatus("Ouverte");
		ticketRepository.save(ticket);
		return "redirect:/client/tickets";	
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute ("ticket") Ticket ticket) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = userRepository.findByUsername(auth.getName());
		ticket.setClient(u);
		ticket.setStatus("Ouverte");
		ticketRepository.save(ticket);
		return "redirect:/client/tickets";	
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		Ticket t =	ticketRepository.getById(id);
		ticketRepository.delete(t);
		return "redirect:/client/tickets";			
	}

}
