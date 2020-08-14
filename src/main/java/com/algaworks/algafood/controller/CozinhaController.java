package com.algaworks.algafood.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.entity.Cozinha;
import com.algaworks.algafood.repository.CozinhaRepository;
import com.algaworks.algafood.service.CozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@GetMapping
	public List<Cozinha> listar() {		
		return cozinhaRepository.findAll();
	}
	
	@GetMapping("/nome")
	public List<Cozinha> listarPorNome(String nome) {		
		return cozinhaRepository.findByNome(nome);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cozinha> buscar(@PathVariable Long id) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
		if (cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());			
		}		
		return ResponseEntity.notFound().build();		
	}
	
	@GetMapping("/primeiro")
	public Optional<Cozinha> primeiroRestaurante() {
		return cozinhaRepository.buscarPrimeiro();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cozinha salvar(@RequestBody Cozinha cozinha) {
		return cozinhaService.salvar(cozinha);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinhaRecebida) {
		Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);
		
		if (cozinhaAtual.isPresent()) {			
			BeanUtils.copyProperties(cozinhaRecebida, cozinhaAtual.get(), "id"); // Do terceiro parâmetro em diante passamos o que queremos que seja ignorado
			Cozinha cozinhaSalva = cozinhaService.salvar(cozinhaAtual.get());
			return ResponseEntity.ok(cozinhaSalva);			
		}
		return ResponseEntity.notFound().build();
	}	

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {		
		cozinhaService.remover(id);		
	}
	
}
