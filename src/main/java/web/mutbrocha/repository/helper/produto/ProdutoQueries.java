package web.mutbrocha.repository.helper.produto;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.mutbrocha.model.Categorias;
import web.mutbrocha.model.Produtos;
import web.mutbrocha.model.filter.CategoriaFilter;
import web.mutbrocha.model.filter.ProdutoFilter;

public interface ProdutoQueries {

	Page<Produtos> pesquisar(ProdutoFilter produtos, Pageable pageable);
	
}
