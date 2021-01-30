package com.alf.api.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "questao")
@Getter
@Setter
public class Questao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String resposta;
	
	@Column(nullable = false)
	private int peso;
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "gabarito_id")
//	private Gabarito gabarito;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

//	public Gabarito getGabarito() {
//		return gabarito;
//	}
//
//	public void setGabarito(Gabarito gabarito) {
//		this.gabarito = gabarito;
//	}
	
	
}
