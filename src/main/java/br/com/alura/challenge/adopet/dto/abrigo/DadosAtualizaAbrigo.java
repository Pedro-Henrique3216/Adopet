package br.com.alura.challenge.adopet.dto.abrigo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosAtualizaAbrigo(
        String nome,

        @Pattern(regexp = "\\S{8,30}", message = "senha deve ter entre 8 e 30 caracteres")
        String senha,

        @Pattern(regexp = "\\d{8,11}", message = "Telefone invalido")
        String telefone,

        @Pattern(regexp = "\\d{8}", message = "formato de cep invalido")
        String cep,

        Long numero

) {
}
