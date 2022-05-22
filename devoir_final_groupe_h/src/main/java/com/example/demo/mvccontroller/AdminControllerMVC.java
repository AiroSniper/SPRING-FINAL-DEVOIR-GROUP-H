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
@RequestMapping("/admin")
public class AdminControllerMVC {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private TicketRepository ticketRepository;

	@GetMapping("/users")
	public String users(Model m) {
		m.addAttribute("users", userRepository.users());
		return "admin/users";	
	}
	
	@GetMapping("/bugs")
	public String nBugs(Model m) {
		m.addAttribute("bugs", userRepository.bugsNotAssigned());
		return "admin/nbugs";	
	}
	@GetMapping("/assignedbugs")
	public String aBugs(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = userRepository.findByUsername(auth.getName());
		m.addAttribute("bugs", userRepository.adminBugs(u.getId()));
		return "admin/bugs";	
	}
	
	
	@GetMapping("/assign/{id}")
	public String assign(Model m,@PathVariable long id) {
	    Ticket t =ticketRepository.getById(id);
		m.addAttribute("ticket", t);
		m.addAttribute("developpers", userRepository.devloppers());
		return "admin/assign";	
	}
	
	@GetMapping("/unassign/{id}")
	public String unassign(Model m,@PathVariable long id) {
	    Ticket t =ticketRepository.getById(id);
		m.addAttribute("ticket", t);
		return "admin/unassign";	
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable long id, Model model) {
		User u = userRepository.user(id);
		if(u != null) {
			model.addAttribute("user",u);
			model.addAttribute("roles",roleRepository.findAll());
			return "admin/user";
		} 
		return "redirect:/admin/users";	
	}
	
	
	@PostMapping("/updaterole")
	public String updaterole(@ModelAttribute ("user") User user) {
		System.out.println(user.toString());
		User u = userRepository.user(user.getId());
		u.setRoles(user.getRoles());
		userService.modifier(u);
		return "redirect:/admin/users";	
	}
	
	@PostMapping("/assignbug")
	public String assignbug(@ModelAttribute ("ticket") Ticket ticket) {
		System.out.println("id "+ticket.getId() + "  dev " + ticket.getDevelopper().getNom() + "  "+ticket.getDevelopper().getNom()+"  "+ticket.getDevelopper().getId());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = userRepository.findByUsername(auth.getName());
		Ticket t = ticketRepository.getById(ticket.getId());
		t.setDevelopper(ticket.getDevelopper());
		t.setAdmin(u);
		t.setStatus("En cours de traitement");
		ticketRepository.save(t);
		return "redirect:/admin/bugs";	
	}
	
	@PostMapping("/unassignbug")
	public String unassignbug(@ModelAttribute ("ticket") Ticket ticket) {
		Ticket t = ticketRepository.getById(ticket.getId());
		t.setDevelopper(null);
		t.setAdmin(null);
		t.setStatus("Ouverte");
		ticketRepository.save(t);
		return "redirect:/admin/assignedbugs";	
	}
}
