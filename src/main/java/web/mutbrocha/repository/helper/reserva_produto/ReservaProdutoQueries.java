package web.mutbrocha.repository.helper.reserva_produto;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import web.mutbrocha.model.ReservaProduto;
import web.mutbrocha.model.Reservas;
import web.mutbrocha.model.filter.ReservaFilter;
import web.mutbrocha.model.filter.ReservaProdutoFilter;

public interface ReservaProdutoQueries {

	Page<ReservaProduto> pesquisar(ReservaProdutoFilter reserva, Pageable pageable);
	
}
