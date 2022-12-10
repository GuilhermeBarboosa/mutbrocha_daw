package web.mutbrocha.model;

public enum Roles {
	
	ROLE_ADM("Adm"),
	ROLE_USER("Usuario");
	
	private String descricao;
	
	private Roles(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
