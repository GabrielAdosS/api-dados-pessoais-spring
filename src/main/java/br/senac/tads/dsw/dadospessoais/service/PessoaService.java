package br.senac.tads.dsw.dadospessoais.service;

import java.util.List;

import br.senac.tads.dsw.dadospessoais.dto.PessoaDto;

public interface PessoaService {

	List<PessoaDto> findAll();

	PessoaDto findByUsername(String username);

	PessoaDto addNew(PessoaDto dto);

	PessoaDto update(String username, PessoaDto pessoa);

	void delete(String username);

}
