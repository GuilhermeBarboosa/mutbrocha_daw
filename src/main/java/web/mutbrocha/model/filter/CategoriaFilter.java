package web.mutbrocha.model.filter;

import java.time.LocalDate;

public class CategoriaFilter {

	private Long id;
	private String categoria;
	
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
	
	@Override
	public String toString() {
		return "CategoriaFilter [id=" + id + ", categoria=" + categoria + "]";
	}



}
