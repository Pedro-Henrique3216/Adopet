package br.com.alura.challenge.adopet.dto;

import br.com.alura.challenge.adopet.model.Tutor;

import java.util.UUID;

public record DadosRetornoTutor(
        UUID id,
        String nome,
        String login,
        String cidade
) {
    public DadosRetornoTutor(Tutor tutor) {
        this(tutor.getId(), tutor.getNome(), tutor.getLogin(), tutor.getCidade());
    }
}