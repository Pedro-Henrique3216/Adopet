package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.dto.adocao.CadastroAdocao;
import br.com.alura.challenge.adopet.dto.adocao.DadosDetalhamentoAdocao;
import br.com.alura.challenge.adopet.model.Adocao;
import br.com.alura.challenge.adopet.service.AdocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {

    @Autowired
    private AdocaoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAdocao> adotar(@RequestBody CadastroAdocao dto, UriComponentsBuilder uriBuilder) {
        Adocao adocao = service.createAdocao(dto);
        URI uri = uriBuilder.path("/adocoes/{id}").buildAndExpand(adocao.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAdocao(adocao));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> cancelarAdocao(@PathVariable UUID id) {
        service.excluirAdocao(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public String excluirAdocao() {
        return "Excluido com sucesso!";
    }
}
