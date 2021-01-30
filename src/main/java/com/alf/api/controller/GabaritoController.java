package com.alf.api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.alf.api.model.Gabarito;
import com.alf.api.model.Questao;
import com.alf.api.repository.GabaritoRepository;
import com.alf.api.repository.QuestaoRepository;

@RestController
@RequestMapping("/gabarito")
public class GabaritoController {
	
	@Autowired
	private GabaritoRepository gabaritoRepository;
	
	@GetMapping
	public List<Gabarito> listar() {
		return gabaritoRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Gabarito adicionar(@RequestBody Gabarito gabarito) {
		int total = 0;
		
		for (Questao questao : gabarito.getQuestoes()) {
			if (questao.getPeso() < 1) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O peso da questão precisa ser maior que 0");
			}
			total += questao.getPeso();
		}
		
		if (total != 10) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O peso total não pode ser diferente de 10");
		}
		
		return gabaritoRepository.save(gabarito);
	}
}
