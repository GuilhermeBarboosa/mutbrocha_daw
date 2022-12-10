package web.mutbrocha.model;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "reserva_produto")
@DynamicUpdate
public class ReservaProduto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(name="gerador6", sequenceName="reserva_produto_id_seq", allocationSize=1)
	@GeneratedValue(generator="gerador6", strategy=GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "reserva")
	private Reservas reserva;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "produto")
	private Produtos produto;

	@Enumerated(EnumType.STRING)
	private Status status = Status.ATIVO;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Reservas getReserva() {
		return reserva;
	}

	public void setReserva(Reservas reserva) {
		this.reserva = reserva;
	}

	public Produtos getProduto() {
		return produto;
	}

	public void setProduto(Produtos produto) {
		this.produto = produto;
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
		ReservaProduto that = (ReservaProduto) o;
		return Objects.equals(id, that.id) && Objects.equals(reserva, that.reserva) && Objects.equals(produto, that.produto) && status == that.status;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, reserva, produto, status);
	}

	@Override
	public String toString() {
		return "ReservaProduto{" +
				"id=" + id +
				", reserva=" + reserva +
				", produto=" + produto +
				", status=" + status +
				'}';
	}
}
