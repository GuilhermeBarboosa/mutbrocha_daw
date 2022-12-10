package web.mutbrocha.model.filter;

import java.time.LocalDate;

public class ProdutoFilter {

	private Long id;
	private String produto;
	private String situacao;
	private Long categoria;
	private Long user;
	
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
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Long getCategoria() {
		return categoria;
	}
	public void setCategoria(Long categoria) {
		this.categoria = categoria;
	}
	public Long getUser() {
		return user;
	}
	public void setUser(Long user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "ProdutoFilter [id=" + id + ", produto=" + produto + ", situacao=" + situacao + ", categoria="
				+ categoria + ", user=" + user + "]";
	}
	




}
