package br.senac.tads.dsw.dadospessoais.security;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UsuarioSistemaService implements UserDetailsService{

	private Map<String, UsuarioSistema> mapUsuarios;
	
	public UsuarioSistemaService() {
        mapUsuarios = new HashMap<>();
        mapUsuarios.put("fulano", new UsuarioSistema("fulano", 
            "Fulano da Silva", "{noop}Abcd%1234",
            List.of(new Permissao("PEAO"))));
        mapUsuarios.put("ciclano", new UsuarioSistema("ciclano",
            "Ciclano de Souza", "{noop}Abcd%1234",
            List.of(new Permissao("PEAO"), new Permissao("GERENTE"))));
        mapUsuarios.put("beltrana", new UsuarioSistema("beltrana",
            "Beltrana dos Santos", "{noop}Abcd%1234",
            List.of(new Permissao("GERENTE"), new Permissao("DIRETOR"))));
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UsuarioSistema user = mapUsuarios.get(username);
		if (user == null) {
			throw new UsernameNotFoundException("Usuário %s não encontrado".formatted(username));
		}
		return user;
	}
}
