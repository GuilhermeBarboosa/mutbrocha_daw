package web.mutbrocha.repository.helper.reserva;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import web.mutbrocha.model.Categorias;
import web.mutbrocha.model.Reservas;
import web.mutbrocha.model.filter.CategoriaFilter;
import web.mutbrocha.model.filter.ReservaFilter;

public interface ReservaQueries {

	Page<Reservas> pesquisar(ReservaFilter reserva, Pageable pageable);
	
}
