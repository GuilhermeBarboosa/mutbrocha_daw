package web.mutbrocha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.mutbrocha.model.Produtos;
import web.mutbrocha.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public void salvar(Produtos produto) {
		produtoRepository.save(produto);
	}
	
	@Transactional
	public void alterar(Produtos produto) {
		produtoRepository.save(produto);
	}
}
