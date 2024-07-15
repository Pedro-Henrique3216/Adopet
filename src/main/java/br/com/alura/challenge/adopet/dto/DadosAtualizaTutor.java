package br.com.alura.challenge.adopet.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosAtualizaTutor(
        String nome,
        String password,
        String cidade,
        String telefone,
        @JsonAlias("sobre_mim")
        String sobreMim
) {
}
