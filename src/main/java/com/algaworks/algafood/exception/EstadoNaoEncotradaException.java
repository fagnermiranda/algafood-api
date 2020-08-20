package com.algaworks.algafood.exception;

public class EstadoNaoEncotradaException extends EntidadeNaoEncotradaException {
	private static final long serialVersionUID = 1L;
	
	public EstadoNaoEncotradaException(String mensagem) {
		super(mensagem);		
	}
	
	public EstadoNaoEncotradaException(Long estadoId) {
		this(String.format("Não exite estado com código %d", estadoId));
	}
	
}
