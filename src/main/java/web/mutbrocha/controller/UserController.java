package web.mutbrocha.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.mutbrocha.ajax.NotificacaoAlertify;
import web.mutbrocha.model.Authorities;
import web.mutbrocha.model.Categorias;
import web.mutbrocha.model.Produtos;
import web.mutbrocha.model.Roles;
import web.mutbrocha.model.Status;
import web.mutbrocha.model.User;
import web.mutbrocha.model.filter.ProdutoFilter;
import web.mutbrocha.model.filter.UserFilter;
import web.mutbrocha.pagination.PageWrapper;
import web.mutbrocha.repository.AuthoritiesRepository;
import web.mutbrocha.repository.CategoriaRepository;
import web.mutbrocha.repository.ProdutoRepository;
import web.mutbrocha.repository.UserRepository;
import web.mutbrocha.service.ProdutoService;
import web.mutbrocha.service.UserService;


@Controller
@RequestMapping("/usuario")
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	@Autowired 
	private UserService userService;
	
	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	@GetMapping("/cadastrar")
	public String abrirCadastro(User user, Model model) {
		return "usuarios/cadastrar";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(@Valid User user, BindingResult resultado, Model model) {

		String erroMensagem = "";
		if (resultado.hasErrors()) {
			logger.info("O usuario recebido para cadastrar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}
			return "usuarios/cadastrar";
		}else{

			if(userRepository.findByusername(user.getUsername()) == null){
				user.setEnabled(true);
				user.setPassword(encoder.encode(user.getPassword()));
				userService.salvar(user);

				Authorities authorities = new Authorities();
				authorities.setUsername(user.getUsername());
				authorities.setAuthority(user.getRoles().toString());
				authoritiesRepository.save(authorities);

				return "redirect:/usuario/cadastro/sucesso";
			}else{
				erroMensagem = "Usuário já cadastrado";

				logger.error(erroMensagem);
				model.addAttribute("erroMensagem", erroMensagem);
				return "usuarios/cadastrar";
			}
		}
	}
	
	@GetMapping("/cadastro/sucesso")
	public String mostrarMensagemCadastroSucesso(Model model) {
		model.addAttribute("mensagem", "Cadastro de usuário efetuado com sucesso.");
		return "mostrarmensagem";
	}


	@GetMapping("/abrirpesquisar")
	public String abrirPesquisa(Model model) {
		return "usuarios/pesquisar";
	}

	@GetMapping("/pesquisar")
	public String pesquisar(UserFilter filtro, Model model, 
			@PageableDefault(size = 10) 
    		@SortDefault(sort = "id", direction = Sort.Direction.ASC)
    		Pageable pageable, HttpServletRequest request) {
		
		Page<User> pagina = userRepository.pesquisar(filtro, pageable);
		PageWrapper<User> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "usuarios/mostrartodas";
	}

	@PostMapping("/abriralterar")
	public String abrirAlterar(User user, Model model) {
		return "usuarios/alterar";
	}
	
	@PostMapping("/alterar")
	public String alterar(User user) {

		User userEscolhido = userRepository.findById(user.getId()).get();
		Authorities authorities = authoritiesRepository.findById(userEscolhido.getUsername()).get();
		authoritiesRepository.deleteById(authorities.getUsername());

		user.setEnabled(true);
		user.setPassword(encoder.encode(user.getPassword()));
		userService.alterar(user);

		Authorities authoritiesNova = new Authorities();
		authoritiesNova.setUsername(user.getUsername());
		authoritiesNova.setAuthority(user.getRoles().toString());
		authoritiesRepository.save(authoritiesNova);

		return "redirect:/usuario/alterar/sucesso";
	}

	@GetMapping("/alterar/sucesso")
	public String mostrarMensagemAlterarSucesso(Model model) {
		model.addAttribute("mensagem", "Alteração na usuários efetuada com sucesso.");
		return "mostrarmensagem";
	}
	
	@PostMapping("/abrirremover")
	public String abrirRemover(User user) {
		return "usuarios/remover";
	}
	
	@PostMapping("/remover")
	public String remover(User user) {
		user.setStatus(Status.INATIVO);
		userService.alterar(user);
		return "redirect:/usuario/remover/sucesso";
	}
	
	@GetMapping("/remover/sucesso")
	public String mostrarMensagemRemoverSucesso(Model model) {
		model.addAttribute("mensagem", "Remoção (INATIVO) de usuário efetuada com sucesso.");
		return "mostrarmensagem";
	}
	
}
