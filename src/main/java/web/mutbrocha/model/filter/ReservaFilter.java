package web.mutbrocha.model.filter;

import java.time.LocalDate;
import java.util.Date;

public class ReservaFilter {

	private Long id;
	private Long usuario;
	private LocalDate data_reserva;
	private LocalDate data_retirada;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
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

	@Override
	public String toString() {
		return "ReservaFilter{" +
				"id=" + id +
				", usuario=" + usuario +
				", data_reserva=" + data_reserva +
				", data_retirada=" + data_retirada +
				'}';
	}
}
