package web.mutbrocha.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import web.mutbrocha.model.Authorities;
import web.mutbrocha.model.Roles;
import web.mutbrocha.model.User;
import web.mutbrocha.repository.AuthoritiesRepository;
import web.mutbrocha.repository.UserRepository;
import web.mutbrocha.service.UserService;

import javax.validation.Valid;

@Controller
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthoritiesRepository authoritiesRepository;

	@Autowired
	private UserService userService;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@GetMapping
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping
	@RequestMapping("/register")
	public String register(User user) {
		return "register";
	}

	@PostMapping
	@RequestMapping("/registerconta")
	public String registerConta(@Valid User user, BindingResult resultado, Model model) {

		String erroMensagem = "";
		if (resultado.hasErrors()) {
			logger.info("O usuario recebido para cadastrar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "register";
		} else {
			if (userRepository.findByusername(user.getUsername()) == null) {
				user.setRoles(Roles.ROLE_USER);
				user.setEnabled(true);
				user.setPassword(encoder.encode(user.getPassword()));
				userService.salvar(user);

				Authorities authorities = new Authorities();
				authorities.setUsername(user.getUsername());
				authorities.setAuthority(user.getRoles().toString());
				authoritiesRepository.save(authorities);

				return "login";
			} else {
				erroMensagem = "Usuário já cadastrado";

				logger.error(erroMensagem);
				model.addAttribute("erroMensagem", erroMensagem);
				return "register";
			}
		}
	}

}
