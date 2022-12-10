package web.mutbrocha.model;

public enum Situacoes{

	RESERVADO("Reservado"),
	DISPONIVEL("Disponivel"),
	COMPRADO("Comprado");
	
	private String descricao;
	
	private Situacoes(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
