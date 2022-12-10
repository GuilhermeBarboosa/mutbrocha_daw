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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "categorias")
@DynamicUpdate
public class Categorias implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="gerador", sequenceName="categorias_id_seq", allocationSize=1)
	@GeneratedValue(generator="gerador", strategy=GenerationType.SEQUENCE)
	private Long id;
	@NotBlank(message = "A categoria não pode ser nula")
	private String categoria;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(categoria, id, status);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categorias other = (Categorias) obj;
		return Objects.equals(categoria, other.categoria) && Objects.equals(id, other.id) && status == other.status;
	}
	
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", categoria=" + categoria + ", status=" + status + "]";
	}

	
	

	
}
