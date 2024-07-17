package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.dto.CadastroAbrigo;
import br.com.alura.challenge.adopet.dto.DadosDetalhamentoAbrigo;
import br.com.alura.challenge.adopet.service.AbrigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/abrigos")
public class AbrigoController {

    @Autowired
    private AbrigoService service;

    @PostMapping
    @Transactional
    public DadosDetalhamentoAbrigo adicionaAbrigo(@RequestBody CadastroAbrigo dto) {
        return service.cadastrarAbrigo(dto);
    }

}
