package web.mutbrocha.repository.helper.produto;

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
import web.mutbrocha.model.Produtos;
import web.mutbrocha.model.Situacoes;
import web.mutbrocha.model.Status;
import web.mutbrocha.model.User;
import web.mutbrocha.model.filter.CategoriaFilter;
import web.mutbrocha.model.filter.ProdutoFilter;
import web.mutbrocha.repository.pagination.PaginacaoUtil;

public class ProdutoQueriesImpl implements ProdutoQueries {

	private static final Logger logger = LoggerFactory.getLogger(ProdutoQueriesImpl.class);
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Produtos> pesquisar(ProdutoFilter filtro, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Produtos> criteriaQuery = builder.createQuery(Produtos.class);
		Root<Produtos> p = criteriaQuery.from(Produtos.class);
		p.fetch("categoria", JoinType.INNER);
//		p.fetch("pessoa", JoinType.INNER);
		TypedQuery<Produtos> typedQuery;
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getId() != null) {
			predicateList.add(builder.equal(p.<Long>get("id"), filtro.getId()));
		}
		if (StringUtils.hasText(filtro.getProduto())) {
			predicateList.add(builder.like(builder.lower(p.<String>get("produto")),
					"%" + filtro.getProduto().toLowerCase() + "%"));
		}
		if (StringUtils.hasText(filtro.getSituacao())) {
			predicateList.add(builder.equal(p.<Situacoes>get("situacao"), Situacoes.valueOf(filtro.getSituacao().toUpperCase())));

		}
		if (filtro.getCategoria() != null) {
			predicateList.add(builder.equal(p.<Categorias>get("categoria").<Long>get("id"), filtro.getCategoria()));
		}
		if (filtro.getUser() != null) {
			predicateList.add(builder.equal(p.<User>get("user").<Long>get("id"), filtro.getUser()));
		}

		predicateList.add(builder.equal(p.<Status>get("status"), Status.ATIVO));

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(p).where(predArray);
		PaginacaoUtil.prepararOrdem(p, criteriaQuery, builder, pageable);
		typedQuery = manager.createQuery(criteriaQuery);
		PaginacaoUtil.prepararIntervalo(typedQuery, pageable);

		List<Produtos> produtos = typedQuery.getResultList();

		long totalProdutos = total(filtro, pageable);

		Page<Produtos> page = new PageImpl<>(produtos, pageable, totalProdutos);

		return page;
	}

	private long total(ProdutoFilter filtro, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = builder.createQuery(Long.class);
		Root<Produtos> p = criteriaQuery.from(Produtos.class);
		Join<Produtos, Categorias> Categoria = p.join("categoria", JoinType.INNER);
		List<Predicate> predicateList = new ArrayList<>();

		if (filtro.getId() != null) {
			predicateList.add(builder.equal(p.<Long>get("id"), filtro.getId()));
		}
		if (StringUtils.hasText(filtro.getProduto())) {
			predicateList.add(builder.like(builder.lower(p.<String>get("produto")),
					"%" + filtro.getProduto().toLowerCase() + "%"));
		}
		if (StringUtils.hasText(filtro.getSituacao())) {
			predicateList.add(builder.equal(p.<Situacoes>get("situacao"), Situacoes.valueOf(filtro.getSituacao().toUpperCase())));

		}
		if (filtro.getCategoria() != null) {
			predicateList.add(builder.equal(p.<Categorias>get("categoria").<Long>get("id"), filtro.getCategoria()));
		}

		predicateList.add(builder.equal(p.<Status>get("status"), Status.ATIVO));

		Predicate[] predArray = new Predicate[predicateList.size()];
		predicateList.toArray(predArray);

		criteriaQuery.select(builder.count(p));
		criteriaQuery.where(predArray);
		TypedQuery<Long> typedQueryTotal = manager.createQuery(criteriaQuery);
		long totalAplicacaoes = typedQueryTotal.getSingleResult();

		return totalAplicacaoes;
	}

}
