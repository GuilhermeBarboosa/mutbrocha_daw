package web.mutbrocha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.mutbrocha.model.Produtos;
import web.mutbrocha.model.Reservas;
import web.mutbrocha.repository.ProdutoRepository;
import web.mutbrocha.repository.ReservaRepository;

@Service
public class ReservaService {
	
	@Autowired
	private ReservaRepository reservaRepository;
	
	@Transactional
	public void salvar(Reservas reserva) {
		reservaRepository.save(reserva);
	}

	@Transactional
	public Reservas salvarComId(Reservas reserva) {
		return reservaRepository.save(reserva);
	}

	@Transactional
	public void alterar(Reservas reserva) {
		reservaRepository.save(reserva);
	}
}
