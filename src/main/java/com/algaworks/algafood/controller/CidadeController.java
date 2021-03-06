package com.algaworks.algafood.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.dto.CidadeDTO;
import com.algaworks.algafood.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

	@Autowired
	private CidadeService service;

	@GetMapping
	public List<CidadeDTO> listar() {
		return service.listar();
	}

	@GetMapping("/{id}")
	public CidadeDTO buscarPorId(@PathVariable Long id) {
		return service.buscarDtoPorId(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping
	public CidadeDTO salvar(@RequestBody @Valid CidadeDTO dto) {
		return service.salvar(dto);
	}

	@PutMapping("/{id}")
	public CidadeDTO atualizar(@PathVariable Long id, @RequestBody @Valid CidadeDTO dto) {
		return service.atualizar(id, dto);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void remover(@PathVariable Long id) {
		service.remover(id);
	}

}
