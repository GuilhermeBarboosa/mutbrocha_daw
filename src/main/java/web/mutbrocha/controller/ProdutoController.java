package web.mutbrocha.controller;

import java.security.Principal;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import web.mutbrocha.model.Categorias;
import web.mutbrocha.model.Produtos;
import web.mutbrocha.model.Roles;
import web.mutbrocha.model.Status;
import web.mutbrocha.model.User;
import web.mutbrocha.model.filter.ProdutoFilter;
import web.mutbrocha.pagination.PageWrapper;
import web.mutbrocha.repository.CategoriaRepository;
import web.mutbrocha.repository.ProdutoRepository;
import web.mutbrocha.repository.UserRepository;
import web.mutbrocha.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired 
	private ProdutoService produtoService;
	
	@GetMapping("/cadastrar")
	public String abrirCadastro(Produtos produtos, Model model) {
		List<Categorias> categorias = categoriaRepository.findByStatus(Status.ATIVO);
		model.addAttribute("categorias", categorias);
		return "produtos/cadastrar";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(Principal principal, @Valid Produtos produtos, BindingResult resultado, Model model) {

		if (resultado.hasErrors()) {
			logger.info("O produto recebido para cadastrar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}

			List<Categorias> categorias = categoriaRepository.findByStatus(Status.ATIVO);
			model.addAttribute("categorias", categorias);
			return "produtos/cadastrar";
		} else {

			User user = userRepository.findByusername(principal.getName());
			produtos.setUser(user);

			produtoService.salvar(produtos);
			return "redirect:/produtos/cadastro/sucesso";
		}
	}
	
	@GetMapping("/cadastro/sucesso")
	public String mostrarMensagemCadastroSucesso(Model model) {
		model.addAttribute("mensagem", "Cadastro de produtos efetuado com sucesso.");
		return "mostrarmensagem";
	}
	
	@GetMapping("/abrirpesquisar")
	public String abrirPesquisa(Model model) {
		List<Categorias> categorias = categoriaRepository.findByStatus(Status.ATIVO);
		model.addAttribute("categorias", categorias);
		
		List<User> users = userRepository.findByStatusAndRole(Status.ATIVO, Roles.ROLE_ADM);
		model.addAttribute("users", users);
		return "produtos/pesquisar";
	}
	
	@GetMapping("/pesquisar")
	public String pesquisar(Categorias categoria, User usuario, ProdutoFilter filtro, Model model, 
			@PageableDefault(size = 10) 
    		@SortDefault(sort = "id", direction = Sort.Direction.ASC)
    		Pageable pageable, HttpServletRequest request) {

		Page<Produtos> pagina = produtoRepository.pesquisar(filtro, pageable);
		PageWrapper<Produtos> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "produtos/mostrartodas";
	}
	
	
	@PostMapping("/abriralterar")
	public String abrirAlterar(Produtos produtos, Model model) {
		logger.error(produtos.toString());
		List<Categorias> listCategorias = categoriaRepository.findByStatus(Status.ATIVO);
		model.addAttribute("categorias", listCategorias);
		return "produtos/alterar";
	}
	
	@PostMapping("/alterar")
	public String alterar(Principal principal, Produtos produtos) {
		User user = userRepository.findByusername(principal.getName());
		produtos.setUser(user);
		
		produtoService.alterar(produtos);
		return "redirect:/produtos/alterar/sucesso";
	}
	
	@GetMapping("/alterar/sucesso")
	public String mostrarMensagemAlterarSucesso(Model model) {
		model.addAttribute("mensagem", "Alteração na produtos efetuada com sucesso.");
		return "mostrarmensagem";
	}
	
	@PostMapping("/abrirremover")
	public String abrirRemover(Produtos produtos) {
		logger.error(produtos.toString());
		return "produtos/remover";
	}
	
	@PostMapping("/remover")
	public String remover(Produtos produtos) {
		produtos.setStatus(Status.INATIVO);
		produtoService.alterar(produtos);
		return "redirect:/produtos/remover/sucesso";
	}
	
	@GetMapping("/remover/sucesso")
	public String mostrarMensagemRemoverSucesso(Model model) {
		model.addAttribute("mensagem", "Remoção (INATIVO) de produtos efetuada com sucesso.");
		return "mostrarmensagem";
	}
}
