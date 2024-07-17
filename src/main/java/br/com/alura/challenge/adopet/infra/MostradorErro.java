package br.com.alura.challenge.adopet.infra;

public record MostradorErro(
        String campo,
        String message
) {
}
