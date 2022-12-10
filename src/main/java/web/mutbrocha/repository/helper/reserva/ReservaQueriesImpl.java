package web.mutbrocha.repository.helper.reserva;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import web.mutbrocha.model.Categorias;
import web.mutbrocha.model.Reservas;
import web.mutbrocha.model.Status;
import web.mutbrocha.model.User;
import web.mutbrocha.model.filter.CategoriaFilter;
import web.mutbrocha.model.filter.ReservaFilter;
import web.mutbrocha.repository.pagination.PaginacaoUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaQueriesImpl implements ReservaQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Reservas> pesquisar(ReservaFilter filtro, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Reservas> criteriaQuery = builder.createQuery(Reservas.class);
		Root<Reservas> p = criteriaQuery.from(Reservas.class);
		TypedQuery<Reservas> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getId() != null) {
			predicateList.add(builder.equal(p.<Long>get("id"), 
		                 filtro.getId()));
		}

		if (filtro.getUsuario() != null) {
			predicateList.add(builder.equal(p.<User>get("usuario").<Long>get("id"),
					filtro.getUsuario()));
		}

		if (filtro.getData_reserva() != null) {
			predicateList.add(builder.greaterThanOrEqualTo(
					p.<LocalDate>get("data_reserva"),
					filtro.getData_reserva()));
		}

		if (filtro.getData_retirada() != null) {
			predicateList.add(builder.greaterThanOrEqualTo(
					p.<LocalDate>get("data_retirada"),
					filtro.getData_retirada()));
		}

		predicateList.add(builder.equal(p.<Status>get("status"), 
		                 Status.ATIVO));
				
		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(p).where(predArray);
		PaginacaoUtil.prepararOrdem(p, criteriaQuery, builder, pageable);
		typedQuery = manager.createQuery(criteriaQuery);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);
								
		List<Reservas> reservas = typedQuery.getResultList();
		
		long totalReservas = PaginacaoUtil.getTotalRegistros(p, predArray, builder, manager);

		Page<Reservas> page = new PageImpl<>(reservas, pageable, totalReservas);
		
		return page;
	}

}
