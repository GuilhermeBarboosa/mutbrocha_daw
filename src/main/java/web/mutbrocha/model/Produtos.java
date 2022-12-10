package web.mutbrocha.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "produtos")
@DynamicUpdate
public class Produtos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="gerador1", sequenceName="produtos_id_seq", allocationSize=1)
	@GeneratedValue(generator="gerador1", strategy=GenerationType.SEQUENCE)
	private Long id;
	@NotBlank(message = "O produto não pode ser nulo")
	private String produto;
	@NotBlank(message = "A imagem não pode ser nula")
	private String img;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "categoria")
	private Categorias categoria;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario")
	private User user;
	
	@Enumerated(EnumType.STRING)
	private Situacoes situacao = Situacoes.DISPONIVEL;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Categorias getCategoria() {
		return categoria;
	}

	public void setCategoria(Categorias categoria) {
		this.categoria = categoria;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Situacoes getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacoes situacao) {
		this.situacao = situacao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return Objects.hash(categoria, id, img, produto, situacao, status, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produtos other = (Produtos) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(id, other.id)
				&& Objects.equals(img, other.img) && Objects.equals(produto, other.produto)
				&& situacao == other.situacao && status == other.status && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Produtos [id=" + id + ", produto=" + produto + ", img=" + img + ", categoria=" + categoria + ", user="
				+ user + ", situacao=" + situacao + ", status=" + status + "]";
	}

	

	
}
