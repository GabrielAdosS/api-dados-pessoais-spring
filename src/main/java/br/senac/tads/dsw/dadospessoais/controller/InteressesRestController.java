package br.senac.tads.dsw.dadospessoais.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.senac.tads.dsw.dadospessoais.entity.InteresseEntity;
import br.senac.tads.dsw.dadospessoais.repository.InteresseRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/interesses")
@CrossOrigin(origins = "*")
public class InteressesRestController {
	@Autowired
	private InteresseRepository repo;
	
	@GetMapping
	public List<String> findAll() {
		return repo.findAll().stream().map(entity -> entity.getNome()).toList();
	}
	
	@PostMapping("/criarInteresse")
	@Transactional
	public ResponseEntity<?> newInteresse() {
        InteresseEntity i1 = new InteresseEntity(1, "Java");
        repo.save(i1);
        repo.save(new InteresseEntity(2, "Spring Boot"));
        repo.save(new InteresseEntity(3, "HTML"));
        repo.save(new InteresseEntity(4, "CSS"));
        repo.save(new InteresseEntity(5, "Javascript"));
        repo.save(new InteresseEntity(6, "SQL"));
        return ResponseEntity.ok().build();
	}
}
