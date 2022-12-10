package web.mutbrocha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import web.mutbrocha.model.Categorias;
import web.mutbrocha.model.Produtos;
import web.mutbrocha.model.Situacoes;
import web.mutbrocha.model.Status;
import web.mutbrocha.repository.helper.categoria.CategoriaQueries;
import web.mutbrocha.repository.helper.produto.ProdutoQueries;


public interface ProdutoRepository extends JpaRepository<Produtos, Long>, ProdutoQueries {

	List<Produtos> findByStatus(Status ativo);

    @Query("from Produtos where status = :status and situacao = :situacoes")
    List<Produtos> findByStatusAndSituacoes(Status status, Situacoes situacoes);
}
