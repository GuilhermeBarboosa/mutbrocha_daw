package web.mutbrocha.model;

import web.mutbrocha.service.UsernameUnicoService;
import web.mutbrocha.validation.UniqueValueAttribute;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@SequenceGenerator(name="gerador4", sequenceName="user_id_seq", allocationSize=1)
	@GeneratedValue(generator="gerador4", strategy=GenerationType.SEQUENCE)
	private Long id;
	@NotBlank(message = "O username do usuário é obrigatório")
	private String username;
	@NotBlank(message = "A senha do usuário é obrigatória")
	private String password;
	@NotBlank(message = "A nome de usuário é obrigatória")
	private String nome;
	private Boolean enabled;
	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;

	@Enumerated(EnumType.STRING)
	private Roles roles;

	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
	@Override
	public int hashCode() {
		return Objects.hash(enabled, id, nome, password, roles, username);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(enabled, other.enabled) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(password, other.password) && roles == other.roles
				&& Objects.equals(username, other.username);
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", nome=" + nome + ", enabled="
				+ enabled + ", roles=" + roles + "]";
	}
	
}
