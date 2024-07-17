package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.dto.CadastroAbrigo;
import br.com.alura.challenge.adopet.dto.DadosDetalhamentoAbrigo;
import br.com.alura.challenge.adopet.model.Abrigo;
import br.com.alura.challenge.adopet.service.AbrigoService;
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
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAbrigo> adicionaAbrigo(@RequestBody CadastroAbrigo dto, UriComponentsBuilder uriComponentsBuilder) {
        Abrigo abrigo = service.cadastrarAbrigo(dto);
        URI uri = uriComponentsBuilder.path("/abrigos/{id}").buildAndExpand(abrigo.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoAbrigo(abrigo));
    }

}
