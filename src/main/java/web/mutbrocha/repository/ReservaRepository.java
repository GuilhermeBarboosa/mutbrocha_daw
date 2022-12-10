package web.mutbrocha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.mutbrocha.controller.ReservaController;
import web.mutbrocha.model.Produtos;
import web.mutbrocha.model.Reservas;
import web.mutbrocha.model.Status;
import web.mutbrocha.repository.helper.produto.ProdutoQueries;
import web.mutbrocha.repository.helper.reserva.ReservaQueries;

import java.util.List;


public interface ReservaRepository extends JpaRepository<Reservas, Long>, ReservaQueries {

	List<Reservas> findByStatus(Status ativo);

}
