package com.alf.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.alf.api.model.Gabarito;
import com.alf.api.model.Prova;
import com.alf.api.model.ProvaAluno;
import com.alf.api.model.Questao;
import com.alf.api.repository.GabaritoRepository;
import com.alf.api.repository.ProvaRepository;

@RestController
@RequestMapping("/prova")
public class ProvaController {

	@Autowired
	private ProvaRepository provaRepository;
	
	@Autowired
	private GabaritoRepository gabaritoRepository;
	
	@GetMapping
	public List<Prova> listar() {
		return provaRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Prova adicionar(@RequestBody ProvaAluno prova) {
		
		Optional<Gabarito> gabaritoOptional = gabaritoRepository.findById(prova.getProva().getGabaritoId());
		System.out.println("gabarito id: " + gabaritoOptional);
		if (gabaritoOptional.isEmpty()) {
			
		}
		
		Gabarito gabarito = gabaritoOptional.get();
		
		List<Questao> respostasCorretas = gabarito.getQuestoes();
		
		int nota = 0;
		
		for (int i = 0; i < prova.getRespostas().size(); i++) {
			if (respostasCorretas.get(i).getResposta().equalsIgnoreCase(prova.getRespostas().get(i))) {
				System.out.println("resposta certa");
				nota += respostasCorretas.get(i).getPeso();
			}
		}
		
		prova.getProva().setNota(nota);
		
		return provaRepository.save(prova.getProva());
	}
}