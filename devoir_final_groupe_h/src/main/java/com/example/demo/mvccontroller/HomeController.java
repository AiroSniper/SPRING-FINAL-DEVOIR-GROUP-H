package com.example.demo.mvccontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public String index(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = userRepository.findByUsername(auth.getName());
		m.addAttribute("user", u);
		return "index";
	}

	@GetMapping("/login")
	public String login() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = userRepository.findByUsername(auth.getName());
		if(u==null)
			return "connexion";
		else {
			return "redirect:/";	
		}
			
	}

	@GetMapping("/inscription")
	public String insc(Model m) {
		m.addAttribute("user", new User());
		return "inscription";
	}
	
	@GetMapping("/profile")
	public String profile(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User u = userRepository.findByUsername(auth.getName());
		m.addAttribute("user", u);
		return "profile";
	}
	
	@GetMapping("/error")
	public String error(Model m) {
		m.addAttribute("user", new User());
		return "error";
	}
	
	@PostMapping("/update_profile")
	public String update(@ModelAttribute ("user") User user) {
		
		User u = userRepository.getById(user.getId());
		u.setNom(user.getNom());
		u.setPrenom(user.getPrenom());
		u.setUsername(u.getUsername());
		userService.modifier(u);
		return "redirect:/";
	}

	@PostMapping("/inscription")
	public String insc(@ModelAttribute User user, BindingResult result) {
		if (result.hasErrors())
			return "inscription";
		userService.save(user);
		return "redirect:login";
	}

}
