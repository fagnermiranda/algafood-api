package com.algaworks.algafood.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

import com.algaworks.algafood.entity.enuns.StatusPedido;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private BigDecimal subtotal;
	
	@Column(nullable = false)
	private BigDecimal taxaFrete;
	
	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCriacao;
	
	private LocalDateTime dataConfirmacao;
	private LocalDateTime dataCancelamento;
	private LocalDateTime dataEntrega;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario usuarioCliente;
	
	@Embedded
	@Column(nullable = false)
	private Endereco enderecoEntrega;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;
		
	@ManyToOne
	@JoinColumn(nullable = false)
	private FormaPagamento formaPagamento;
	
	private StatusPedido status;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens = new ArrayList<>();

}