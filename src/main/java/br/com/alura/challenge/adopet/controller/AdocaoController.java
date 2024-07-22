package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.dto.adocao.CadastroAdocao;
import br.com.alura.challenge.adopet.dto.adocao.DadosDetalhamentoAdocao;
import br.com.alura.challenge.adopet.service.AdocaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adocoes")
public class AdocaoController {

    @Autowired
    private AdocaoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoAdocao> adotar(@RequestBody CadastroAdocao dto) {
        return ResponseEntity.ok(service.createAdocao(dto));
    }


}
