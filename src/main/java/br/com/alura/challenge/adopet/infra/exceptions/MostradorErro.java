package br.com.alura.challenge.adopet.infra.exceptions;

public record MostradorErro(
        String campo,
        String message
) {
}
