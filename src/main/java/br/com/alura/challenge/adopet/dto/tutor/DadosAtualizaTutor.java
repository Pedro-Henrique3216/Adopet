package br.com.alura.challenge.adopet.dto.tutor;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DadosAtualizaTutor(
        String nome,
        String password,
        String cidade,
        String telefone,
        @JsonAlias("sobre_mim")
        String sobreMim
) {
}
