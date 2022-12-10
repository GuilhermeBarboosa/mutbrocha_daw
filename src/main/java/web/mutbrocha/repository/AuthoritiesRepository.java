package web.mutbrocha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import web.mutbrocha.model.Authorities;
import web.mutbrocha.model.User;

public interface AuthoritiesRepository  extends JpaRepository<Authorities, String> {

    Authorities findByusername(String username);
}
