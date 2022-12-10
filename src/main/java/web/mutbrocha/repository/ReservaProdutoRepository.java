package web.mutbrocha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.mutbrocha.model.ReservaProduto;
import web.mutbrocha.model.Reservas;
import web.mutbrocha.model.Status;
import web.mutbrocha.repository.helper.reserva.ReservaQueries;
import web.mutbrocha.repository.helper.reserva_produto.ReservaProdutoQueries;

import java.util.List;


public interface ReservaProdutoRepository extends JpaRepository<ReservaProduto, Long>, ReservaProdutoQueries {

	List<ReservaProduto> findByStatus(Status ativo);

}
