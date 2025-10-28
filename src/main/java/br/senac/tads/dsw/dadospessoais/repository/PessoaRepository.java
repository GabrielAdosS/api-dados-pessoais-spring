package br.senac.tads.dsw.dadospessoais.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.senac.tads.dsw.dadospessoais.entity.PessoaEntity;

public interface PessoaRepository extends JpaRepository<PessoaEntity, Integer>{
	Optional<PessoaEntity> findByUsername(String username);
}
