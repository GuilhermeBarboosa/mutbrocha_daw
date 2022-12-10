package web.mutbrocha.model.filter;

import java.time.LocalDate;

public class UserFilter {

	private Long id;
	private String nome;
	private String username;
	private String roles;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	@Override
	public String toString() {
		return "UserFilter [id=" + id + ", nome=" + nome + ", username=" + username + ", roles=" + roles + "]";
	}
	
	
}
