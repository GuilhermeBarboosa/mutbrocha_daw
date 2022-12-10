package web.mutbrocha.model.filter;

import java.time.LocalDate;

public class ReservaProdutoFilter {

	private Long id;
	private Long usuario;
	private Long produto;

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

	public Long getProduto() {
		return produto;
	}

	public void setProduto(Long produto) {
		this.produto = produto;
	}

	@Override
	public String toString() {
		return "ReservaProdutoFilter [id=" + id + ", usuario=" + usuario + ", produto=" + produto + "]";
	}

	
	
}
