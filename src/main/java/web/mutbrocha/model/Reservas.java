package web.mutbrocha.model;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reservas")
@DynamicUpdate
public class Reservas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="gerador5", sequenceName="reservas_id_seq", allocationSize=1)
	@GeneratedValue(generator="gerador5", strategy=GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "usuario")
	private User user;

	private LocalDate data_reserva;
	private LocalDate data_retirada;

	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getData_reserva() {
		return data_reserva;
	}

	public void setData_reserva(LocalDate data_reserva) {
		this.data_reserva = data_reserva;
	}

	public LocalDate getData_retirada() {
		return data_retirada;
	}

	public void setData_retirada(LocalDate data_retirada) {
		this.data_retirada = data_retirada;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Reservas reservas = (Reservas) o;
		return Objects.equals(id, reservas.id) && Objects.equals(user, reservas.user) && Objects.equals(data_reserva, reservas.data_reserva) && Objects.equals(data_retirada, reservas.data_retirada) && status == reservas.status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, user, data_reserva, data_retirada, status);
	}

	@Override
	public String toString() {
		return "Reservas{" +
				"id=" + id +
				", user=" + user +
				", data_reserva=" + data_reserva +
				", data_retirada=" + data_retirada +
				", status=" + status +
				'}';
	}
}
