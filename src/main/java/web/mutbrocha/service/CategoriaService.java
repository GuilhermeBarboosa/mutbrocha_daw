package web.mutbrocha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.mutbrocha.model.Categorias;
import web.mutbrocha.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Transactional
	public void salvar(Categorias categoria) {
		categoriaRepository.save(categoria);
	}
	
	@Transactional
	public void alterar(Categorias categoria) {
		categoriaRepository.save(categoria);
	}
}
