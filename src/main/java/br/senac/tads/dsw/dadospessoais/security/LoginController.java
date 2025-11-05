package br.senac.tads.dsw.dadospessoais.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	private AuthenticationManager manager;
	
	@PostMapping("/login")
	public ResponseEntity<RespostaLogin> fazerLogin(@RequestBody Credencial credencial) {
        Authentication auth = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credencial.username(),
                        credencial.senha()));
        if(auth == null) {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UsuarioSistema user = (UsuarioSistema) auth.getPrincipal();
        return ResponseEntity.ok().body(new RespostaLogin(user.getNomeCompleto(), "token123"));
	}
	
	public record Credencial(String username, String senha) { }

	public record RespostaLogin(String nome, String token) { }
}