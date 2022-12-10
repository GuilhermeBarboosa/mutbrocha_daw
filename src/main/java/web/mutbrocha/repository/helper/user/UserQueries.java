package web.mutbrocha.repository.helper.user;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import web.mutbrocha.model.Categorias;
import web.mutbrocha.model.User;
import web.mutbrocha.model.filter.CategoriaFilter;
import web.mutbrocha.model.filter.UserFilter;

public interface UserQueries {

	Page<User> pesquisar(UserFilter user, Pageable pageable);
	
}
