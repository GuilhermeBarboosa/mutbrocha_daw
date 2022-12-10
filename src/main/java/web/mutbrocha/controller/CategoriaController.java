package web.mutbrocha.controller;

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
import web.mutbrocha.model.Status;
import web.mutbrocha.model.User;
import web.mutbrocha.model.filter.CategoriaFilter;
import web.mutbrocha.pagination.PageWrapper;
import web.mutbrocha.repository.CategoriaRepository;
import web.mutbrocha.service.CategoriaService;

import java.util.List;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

	private static final Logger logger = LoggerFactory.getLogger(CategoriaController.class);
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired 
	private CategoriaService categoriaService;
	
	@GetMapping("/cadastrar")
	public String abrirCadastro(Categorias categorias) {
		return "categorias/cadastrar";
	}
	
	@PostMapping("/cadastrar")
	public String cadastrar(@Valid Categorias categoria, BindingResult resultado, Model model) {


		if (resultado.hasErrors()) {
			logger.info("A categoria recebida para cadastrar não é válido.");
			logger.info("Erros encontrados:");
			for (FieldError erro : resultado.getFieldErrors()) {
				logger.info("{}", erro);
			}

			return "categorias/cadastrar";
		} else {
			categoriaService.salvar(categoria);
			return "redirect:/categorias/cadastro/sucesso";
		}

		 

	}
	
	@GetMapping("/cadastro/sucesso")
	public String mostrarMensagemCadastroSucesso(Model model) {
		model.addAttribute("mensagem", "Cadastro de categoria efetuado com sucesso.");
		return "mostrarmensagem";
	}
	
	@GetMapping("/abrirpesquisar")
	public String abrirPesquisa() {
		return "categorias/pesquisar";
	}
	
	@GetMapping("/pesquisar")
	public String pesquisar(CategoriaFilter filtro, Model model, 
			@PageableDefault(size = 10) 
    		@SortDefault(sort = "id", direction = Sort.Direction.ASC)
    		Pageable pageable, HttpServletRequest request) {
		
		Page<Categorias> pagina = categoriaRepository.pesquisar(filtro, pageable);
		PageWrapper<Categorias> paginaWrapper = new PageWrapper<>(pagina, request);
		model.addAttribute("pagina", paginaWrapper);
		return "categorias/mostrartodas";
	}
	
	
	@PostMapping("/abriralterar")
	public String abrirAlterar(Categorias categorias) {
		return "categorias/alterar";
	}
	
	@PostMapping("/alterar")
	public String alterar(Categorias categorias) {
		categoriaService.alterar(categorias);
		return "redirect:/categorias/alterar/sucesso";
	}
	
	@GetMapping("/alterar/sucesso")
	public String mostrarMensagemAlterarSucesso(Model model) {
		model.addAttribute("mensagem", "Alteração na categoria efetuada com sucesso.");
		return "mostrarmensagem";
	}
	
	@PostMapping("/abrirremover")
	public String abrirRemover(Categorias categorias) {
		return "categorias/remover";
	}
	
	@PostMapping("/remover")
	public String remover(Categorias categorias) {
		categorias.setStatus(Status.INATIVO);
		categoriaService.alterar(categorias);
		return "redirect:/categorias/remover/sucesso";
	}
	
	@GetMapping("/remover/sucesso")
	public String mostrarMensagemRemoverSucesso(Model model) {
		model.addAttribute("mensagem", "Remoção (INATIVO) de categoria efetuada com sucesso.");
		return "mostrarmensagem";
	}
}
