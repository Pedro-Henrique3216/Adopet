package br.com.alura.challenge.adopet.dto;

import br.com.alura.challenge.adopet.model.Abrigo;

import java.util.UUID;

public record DadosRetornoAbrigo(
        UUID id,
        String nome,
        String email,
        String telefone
) {
    public DadosRetornoAbrigo(Abrigo abrigo) {
        this(abrigo.getId(), abrigo.getNome(), abrigo.getLogin(), abrigo.getTelefone());
    }
}
