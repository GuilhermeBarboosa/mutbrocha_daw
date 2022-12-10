package web.mutbrocha.repository.helper.user;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import web.mutbrocha.controller.ProdutoController;
import web.mutbrocha.model.Categorias;
import web.mutbrocha.model.Roles;
import web.mutbrocha.model.Situacoes;
import web.mutbrocha.model.Status;
import web.mutbrocha.model.User;
import web.mutbrocha.model.filter.CategoriaFilter;
import web.mutbrocha.model.filter.UserFilter;
import web.mutbrocha.repository.pagination.PaginacaoUtil;

public class UserQueriesImpl implements UserQueries {

	private static final Logger logger = LoggerFactory.getLogger(UserQueriesImpl.class);
	
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<User> pesquisar(UserFilter filtro, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<User> criteriaQuery = builder.createQuery(User.class);
		Root<User> p = criteriaQuery.from(User.class);
		TypedQuery<User> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

//		logger.debug("ID DO USUARIO" + filtro.getId().toString());
		if (filtro.getId() != null) {
			predicateList.add(builder.equal(p.<Long>get("id"), 
		                 filtro.getId()));
		}
		
		if (StringUtils.hasText(filtro.getNome())) {
			predicateList.add(builder.like(	builder.lower(p.<String>get("nome")),
										"%" + filtro.getNome().toLowerCase() + "%"));
		}
		
		if (StringUtils.hasText(filtro.getUsername())) {
			predicateList.add(builder.like(	builder.lower(p.<String>get("username")),
										"%" + filtro.getUsername().toLowerCase() + "%"));
		}
		
		if (StringUtils.hasText(filtro.getRoles())) {
			predicateList.add(builder.equal(p.<Roles>get("roles"), Roles.valueOf(filtro.getRoles().toUpperCase())));

		}
		
		predicateList.add(builder.equal(p.<Status>get("status"), 
		                 Status.ATIVO));
				
		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(p).where(predArray);
		PaginacaoUtil.prepararOrdem(p, criteriaQuery, builder, pageable);
		typedQuery = manager.createQuery(criteriaQuery);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);
								
		List<User> users = typedQuery.getResultList();
		
		long totalUser = PaginacaoUtil.getTotalRegistros(p, predArray, builder, manager);

		Page<User> page = new PageImpl<>(users, pageable, totalUser); 
		
		return page;
	}

}
