package br.com.alura.challenge.adopet.controller;

import br.com.alura.challenge.adopet.dto.AutenticacaoDTO;
import br.com.alura.challenge.adopet.dto.RetornoLoginDTO;
import br.com.alura.challenge.adopet.infra.security.TokenService;
import br.com.alura.challenge.adopet.model.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<RetornoLoginDTO> login (@RequestBody @Valid AutenticacaoDTO dto){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        Authentication autenticacao = authenticationManager.authenticate(usernamePassword);
        String token = tokenService.tokenCreate((User) autenticacao.getPrincipal());
        return ResponseEntity.ok(new RetornoLoginDTO(token));
    }
}
