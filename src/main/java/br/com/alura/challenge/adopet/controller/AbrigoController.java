package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.dto.abrigo.CadastroAbrigo;
import br.com.alura.challenge.adopet.dto.abrigo.DadosAtualizaAbrigo;
import br.com.alura.challenge.adopet.dto.abrigo.DadosDetalhamentoAbrigo;
import br.com.alura.challenge.adopet.dto.abrigo.DadosRetornoAbrigo;
import br.com.alura.challenge.adopet.model.Abrigo;
import br.com.alura.challenge.adopet.service.AbrigoService;
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
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAbrigo> adicionaAbrigo(@RequestBody @Valid CadastroAbrigo dto, UriComponentsBuilder uriComponentsBuilder) {
        Abrigo abrigo = service.cadastrarAbrigo(dto);
        URI uri = uriComponentsBuilder.path("/abrigos/{id}").buildAndExpand(abrigo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAbrigo(abrigo));
    }

    @GetMapping
    public ResponseEntity<Page<DadosRetornoAbrigo>> listarAbrigos(Pageable pageable) {
        return ResponseEntity.ok(service.listarAbrigos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoAbrigo> buscarAbrigoPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarAbrigoPorId(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoAbrigo> atualizarAbrigo(@PathVariable UUID id, @RequestBody @Valid DadosAtualizaAbrigo dto) {
        return ResponseEntity.ok(service.atualizarAbrigo(id, dto));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
