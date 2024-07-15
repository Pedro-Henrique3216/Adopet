package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.dto.CadastroTutor;
import br.com.alura.challenge.adopet.model.Tutor;
import br.com.alura.challenge.adopet.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/tutor")
public class TutorController {

    @Autowired
    private TutorService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Tutor> create(@RequestBody @Valid CadastroTutor dto, UriComponentsBuilder uriBuilder) {
        Tutor tutor = service.cadastrarTutor(dto);
        URI uri = uriBuilder.path("/tutor/{id}").buildAndExpand(tutor.getId()).toUri();
        return ResponseEntity.created(uri).body(tutor);
    }
}
