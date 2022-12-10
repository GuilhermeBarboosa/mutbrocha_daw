package web.mutbrocha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import web.mutbrocha.model.Categorias;
import web.mutbrocha.model.Status;
import web.mutbrocha.repository.helper.categoria.CategoriaQueries;


public interface CategoriaRepository extends JpaRepository<Categorias, Long>, CategoriaQueries {

	List<Categorias> findByStatus(Status ativo);

}
