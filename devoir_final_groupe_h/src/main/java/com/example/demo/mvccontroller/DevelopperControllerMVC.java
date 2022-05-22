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
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.TicketRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/developper")
public class DevelopperControllerMVC {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping("/bugs")
	public String nBugs(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = userRepository.findByUsername(auth.getName());
		m.addAttribute("bugs", userRepository.devBugs(u.getId()));
		return "developper/bugs";	
	}
	
	@GetMapping("/edit/{id}")
	public String edit(Model m, @PathVariable long id) {
	    Ticket t =	ticketRepository.getById(id);
	    m.addAttribute("ticket", t);
		return "developper/update";	
	}
	
	@PostMapping("/update")
	public String update(@ModelAttribute ("ticket") Ticket ticket) {
		System.out.println(ticket.toString());
		Ticket t = ticketRepository.getById(ticket.getId());
		t.setStatus(ticket.getStatus());
		ticketRepository.save(t);
		return "redirect:/developper/bugs";	
	}
}
