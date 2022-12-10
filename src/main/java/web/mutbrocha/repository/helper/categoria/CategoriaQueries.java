package web.mutbrocha.repository.helper.categoria;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.mutbrocha.model.Categorias;
import web.mutbrocha.model.filter.CategoriaFilter;

public interface CategoriaQueries {

	Page<Categorias> pesquisar(CategoriaFilter categoria, Pageable pageable);
	
}
