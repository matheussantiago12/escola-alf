package com.alf.api.controller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.alf.api.model.Gabarito;
import com.alf.api.model.Prova;
import com.alf.api.model.Questao;
import com.alf.api.repository.GabaritoRepository;
import com.alf.api.repository.ProvaRepository;

@RestController
@RequestMapping("/boletim")
public class BoletimController {
	
	@Autowired
	private ProvaRepository provaRepository;
	
	private Map<String, Double> getMediaAlunos() {
		List<Prova> alunos = provaRepository.findAll();
		Map<String, List<Prova>> alunosAgrupados =
			    alunos.stream().collect(Collectors.groupingBy(w -> w.getAluno()));
		
		Map<String, Double> mediasAlunos = new HashMap<String, Double>();
		
		for (Map.Entry<String, List<Prova>> entry : alunosAgrupados.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue().toString());
			
			Double media = 0.0;
			
			for (Prova prova : entry.getValue()) {
				media += prova.getNota();
			}
			
			media /= entry.getValue().size();
			
			mediasAlunos.put(entry.getKey(), media);
		}
		
		return mediasAlunos;
	}
	
	@GetMapping
	public Map<String, Double> listarMediaAlunos() {
		return getMediaAlunos();
	}
	
	@GetMapping
	@RequestMapping("/aprovados")
	public List<String> listarAprovados() {
		List<String> aprovados = new ArrayList<>();
		
		for (Map.Entry<String, Double> entry : getMediaAlunos().entrySet()) {
			if (entry.getValue() < 7)
				continue;
			
			aprovados.add(entry.getKey());
		}
		
		return aprovados;
	}
}
