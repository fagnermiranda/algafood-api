package com.algaworks.algafood.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.dto.PedidoDTO;
import com.algaworks.algafood.dto.PedidoResumoDTO;
import com.algaworks.algafood.filtro.PedidoFiltro;
import com.algaworks.algafood.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService service;
	
	@GetMapping("/{codigo}")
	public PedidoDTO buscarDtoPorCodigo(@PathVariable String codigo) {
		return service.buscarDtoPorCodigo(codigo);
	}
	
	@GetMapping
	public Page<PedidoResumoDTO> listar(Pageable pageable) {
		return service.listar(pageable);
	}
	
	/*
	 * Só pelo fato de colocar o PedidoFiltro como parametro, o spring já instancia um PedidoFiltro
	 * e atribui quando passado na url um clienteId e restauranteId a esse PedidoFiltro criado automaticamente.
	 * pois são atributos do PedidoFiltro, assim jã são identificados.
	 */
	@GetMapping("/com-filtro")
	public List<PedidoResumoDTO> pesquisarComFiltro(PedidoFiltro filtro) {
		return service.pesquisarComFiltro(filtro);
	}
	
	@PostMapping
	public PedidoResumoDTO criarPedido(@Valid @RequestBody PedidoDTO dto) {
		return service.criarPedido(dto);
	}
	
	@PutMapping("/{codigo}/confirmar-pedido")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmarPedido(@PathVariable String codigo) {
		service.confirmarPedido(codigo);
	}
	
	@PutMapping("/{codigo}/confirmar-entrega")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void confirmarEntrega(@PathVariable String codigo) {
		service.confirmarEntrega(codigo);
	}
	
	@PutMapping("/{codigo}/cancelar-pedido")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancelarPedido(@PathVariable String codigo) {
		service.cancelarPedido(codigo);
	}
	
}
