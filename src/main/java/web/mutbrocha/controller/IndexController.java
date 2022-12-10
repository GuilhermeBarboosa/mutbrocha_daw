package web.mutbrocha.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import web.mutbrocha.model.User;
import web.mutbrocha.repository.UserRepository;

@Controller
public class IndexController {
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping(value = {"/", "/index.html"} )
	public String index(Model model) {
		return "index";
	}
	
}