package br.com.esig.model;

public enum Prioridade {
	
	BAIXA("Baixa"), 
	MEDIA("Media"),
	ALTA("Alta");

	
	private String nivel;

	Prioridade(String descricao) {
		this.nivel = descricao;
	}
	
	public String getDescricao() {
		return nivel;
	}

}
