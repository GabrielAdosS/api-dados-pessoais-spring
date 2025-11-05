package br.senac.tads.dsw.dadospessoais.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.tads.dsw.dadospessoais.entity.InteresseEntity;

public interface InteresseRepository extends JpaRepository<InteresseEntity, Integer>{
	
	Optional<InteresseEntity> findByNomeIgnoreCase(String nome);

}
