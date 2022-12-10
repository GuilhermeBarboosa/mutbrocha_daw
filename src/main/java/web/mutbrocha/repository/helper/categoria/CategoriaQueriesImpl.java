package web.mutbrocha.repository.helper.categoria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;


import web.mutbrocha.model.Categorias;
import web.mutbrocha.model.Status;
import web.mutbrocha.model.filter.CategoriaFilter;
import web.mutbrocha.repository.pagination.PaginacaoUtil;

public class CategoriaQueriesImpl implements CategoriaQueries {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<Categorias> pesquisar(CategoriaFilter filtro, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Categorias> criteriaQuery = builder.createQuery(Categorias.class);
		Root<Categorias> p = criteriaQuery.from(Categorias.class);
		TypedQuery<Categorias> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getId() != null) {
			predicateList.add(builder.equal(p.<Long>get("id"), 
		                 filtro.getId()));
		}
		
		if (StringUtils.hasText(filtro.getCategoria())) {
			predicateList.add(builder.like(	builder.lower(p.<String>get("categoria")),
										"%" + filtro.getCategoria().toLowerCase() + "%"));
		}
		
		predicateList.add(builder.equal(p.<Status>get("status"), 
		                 Status.ATIVO));
				
		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(p).where(predArray);
		PaginacaoUtil.prepararOrdem(p, criteriaQuery, builder, pageable);
		typedQuery = manager.createQuery(criteriaQuery);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);
								
		List<Categorias> categorias = typedQuery.getResultList();
		
		long totalCategorias = PaginacaoUtil.getTotalRegistros(p, predArray, builder, manager);

		Page<Categorias> page = new PageImpl<>(categorias, pageable, totalCategorias); 
		
		return page;
	}

}
