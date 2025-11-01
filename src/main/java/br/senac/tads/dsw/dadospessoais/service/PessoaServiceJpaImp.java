package br.senac.tads.dsw.dadospessoais.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.senac.tads.dsw.dadospessoais.dto.PessoaDto;
import br.senac.tads.dsw.dadospessoais.entity.PessoaEntity;
import br.senac.tads.dsw.dadospessoais.repository.PessoaRepository;
import br.senac.tads.dsw.dadospessoais.service.interfaces.PessoaService;

@Service
public class PessoaServiceJpaImp implements PessoaService{
	@Autowired
	private PessoaRepository repository;

	private PessoaDto toDto(PessoaEntity entity) {
		PessoaDto dto = new PessoaDto();
		dto.setUsername(entity.getUsername());
		dto.setNome(entity.getNome());
		dto.setDataNascimento(entity.getDataNasc());
		dto.setEmail(entity.getEmail());
		dto.setTelefone(entity.getTelefone());
		return dto;
	}
	
	 @Override
	    public List<PessoaDto> findAll() {
	        List<PessoaEntity> entities = repository.findAll();
	        List<PessoaDto> resultado = new ArrayList();
	        for (PessoaEntity entity : entities) {
	            resultado.add(toDto(entity));
	        }
	        return resultado;
	    }

	    @Override
	    public PessoaDto findByUsername(String username) {
	        Optional<PessoaEntity> optPessoa = repository.findByUsername(username);
	        if (optPessoa.isEmpty()) {
	            // Tratar erro
	            return null;
	        }
	        PessoaEntity entity = optPessoa.get();
	        return toDto(entity);
	    }

	    @Override
	    public PessoaDto addNew(PessoaDto dto) {

	        PessoaEntity entity = new PessoaEntity();
	        entity.setUsername(dto.getUsername());
	        entity.setNome(dto.getNome());
	        entity.setDataNasc(dto.getDataNascimento());
	        entity.setEmail(dto.getEmail());
	        entity.setTelefone(dto.getTelefone());
	        entity.setSenha(dto.getSenha());
	        // TODO: setar interesses

	        repository.save(entity);
	        return dto;
	    }

	    @Override
	    public PessoaDto update(String username, PessoaDto pessoa) {
	        // TODO Auto-generated method stub
	        throw new UnsupportedOperationException("Unimplemented method 'update'");
	    }

	    @Override
	    public void delete(String username) {
	        // TODO Auto-generated method stub
	        throw new UnsupportedOperationException("Unimplemented method 'delete'");
	    }
}
