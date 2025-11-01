package br.senac.tads.dsw.dadospessoais.service.interfaces;

import java.util.List;

import br.senac.tads.dsw.dadospessoais.dto.PessoaDto;

public interface PessoaService {

	List<PessoaDto> findAll();

	PessoaDto findByUsername(String username);

	PessoaDto addNew(PessoaDto dto);

	PessoaDto update(String username, PessoaDto pessoa);

	void delete(String username);

}
