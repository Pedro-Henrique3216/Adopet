package br.com.alura.challenge.adopet.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AutenticacaoDTO(
        @NotBlank(message = "login não pode ser em branco ou nulo")
        @Email(message = "Formato de login invalido")
        String username,
        @NotBlank(message = "senha não pode ser em branco ou nulo")
        @Pattern(regexp = "\\S{8,30}", message = "senha deve ter entre 8 e 30 digitos")
        String password
) {
}
