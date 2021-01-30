package com.alf.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alf.api.model.Gabarito;
import com.alf.api.model.Questao;

@Repository
public interface GabaritoRepository extends JpaRepository<Gabarito, Long> {
	
}
