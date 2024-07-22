package br.com.alura.challenge.adopet.dto.adocao;

import java.util.UUID;

public record CadastroAdocao(
        UUID animal,
        UUID tutor
) {
}
