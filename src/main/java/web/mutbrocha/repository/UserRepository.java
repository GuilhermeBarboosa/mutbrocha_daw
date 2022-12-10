package web.mutbrocha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import web.mutbrocha.model.Produtos;
import web.mutbrocha.model.Roles;
import web.mutbrocha.model.Situacoes;
import web.mutbrocha.model.Status;
import web.mutbrocha.model.User;
import web.mutbrocha.repository.helper.user.UserQueries;


@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserQueries{
	
	User findByusername(String username);

	List<User> findByStatus(Status ativo);
	
	 @Query("from User where status = :status and roles = :roles")
	 List<User> findByStatusAndRole(Status status, Roles roles);
}
