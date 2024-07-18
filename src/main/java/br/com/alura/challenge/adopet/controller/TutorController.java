package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.dto.tutor.CadastroTutor;
import br.com.alura.challenge.adopet.dto.tutor.DadosAtualizaTutor;
import br.com.alura.challenge.adopet.dto.tutor.DadosRetornoTutor;
import br.com.alura.challenge.adopet.model.Tutor;
import br.com.alura.challenge.adopet.service.TutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/tutores")
public class TutorController {

    @Autowired
    private TutorService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosRetornoTutor> create(@RequestBody @Valid CadastroTutor dto, UriComponentsBuilder uriBuilder) {
        Tutor tutor = service.cadastrarTutor(dto);
        URI uri = uriBuilder.path("/tutor/{id}").buildAndExpand(tutor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosRetornoTutor(tutor));
    }

    @GetMapping
    public ResponseEntity<Page<DadosRetornoTutor>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosRetornoTutor> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosRetornoTutor> update(@PathVariable UUID id, @RequestBody DadosAtualizaTutor dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
