package web.mutbrocha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.mutbrocha.model.ReservaProduto;
import web.mutbrocha.model.Reservas;
import web.mutbrocha.repository.ReservaProdutoRepository;
import web.mutbrocha.repository.ReservaRepository;

@Service
public class ReservaProdutoService {
	
	@Autowired
	private ReservaProdutoRepository reservaProdutoRepository;
	
	@Transactional
	public void salvar(ReservaProduto reservaProduto) {
		reservaProdutoRepository.save(reservaProduto);
	}
	
	@Transactional
	public void alterar(ReservaProduto reservaProduto) {
		reservaProdutoRepository.save(reservaProduto);
	}
}
