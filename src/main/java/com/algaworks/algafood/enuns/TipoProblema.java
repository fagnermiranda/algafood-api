package com.algaworks.algafood.enuns;

import lombok.Getter;

/*
 * Quando crio um construtor em um enum preciso passar os atributos do construtor na frente.
 * */

@Getter
public enum TipoProblema {
	CORPO_ILEGIVEL("Corpo ilegível", "corpo-ilegivel"),
	ENTIDADE_NAO_ENCONTRADA("Entidade nao encontrada", "/entidade-nao-encontrada"),
	ENTIDADE_EM_USO("Entidade em uso", "/entidade-em-uso"),
	NEGOCIO("Negócio", "/negocio");
	
	private String titulo;
	private String uri;
	
	private TipoProblema(String titulo, String path) {
		this.titulo = titulo;
		this.uri = "https://algafood.com.br" + path;
	}
	
	
}
