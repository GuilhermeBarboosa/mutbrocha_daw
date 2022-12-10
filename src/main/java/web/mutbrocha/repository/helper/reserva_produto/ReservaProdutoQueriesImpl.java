package web.mutbrocha.repository.helper.reserva_produto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import web.mutbrocha.controller.ReservaController;
import web.mutbrocha.model.Produtos;
import web.mutbrocha.model.ReservaProduto;
import web.mutbrocha.model.Reservas;
import web.mutbrocha.model.Status;
import web.mutbrocha.model.User;
import web.mutbrocha.model.filter.ReservaFilter;
import web.mutbrocha.model.filter.ReservaProdutoFilter;
import web.mutbrocha.repository.pagination.PaginacaoUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservaProdutoQueriesImpl implements ReservaProdutoQueries {

	  private static final Logger logger = LoggerFactory.getLogger(ReservaProdutoQueriesImpl.class);

	  
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<ReservaProduto> pesquisar(ReservaProdutoFilter filtro, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ReservaProduto> criteriaQuery = builder.createQuery(ReservaProduto.class);
		Root<ReservaProduto> p = criteriaQuery.from(ReservaProduto.class);
		TypedQuery<ReservaProduto> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();
		
		
		if (filtro.getId() != null) {
			predicateList.add(builder.equal(p.<Long>get("id"), 
		                 filtro.getId()));
		}

		if (filtro.getUsuario() != null) {
			predicateList.add(builder.equal(p.<Reservas>get("reserva").<User>get("user").<Long>get("id"),
					filtro.getUsuario()));
		}
		
		if (filtro.getProduto() != null) {

			predicateList.add(builder.equal(p.<Produtos>get("produto").<Long>get("id"),
					filtro.getProduto()));
		}

		predicateList.add(builder.equal(p.<Status>get("status"), 
		                 Status.ATIVO));
				
		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(p).where(predArray);
		PaginacaoUtil.prepararOrdem(p, criteriaQuery, builder, pageable);
		typedQuery = manager.createQuery(criteriaQuery);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);
								
		List<ReservaProduto> reservaProdutos = typedQuery.getResultList();
		
		long totalReservasProdutos = PaginacaoUtil.getTotalRegistros(p, predArray, builder, manager);

logger.error("Total de reservas" + totalReservasProdutos);
		
		Page<ReservaProduto> page = new PageImpl<>(reservaProdutos, pageable, totalReservasProdutos);
		
		return page;
	}

}
