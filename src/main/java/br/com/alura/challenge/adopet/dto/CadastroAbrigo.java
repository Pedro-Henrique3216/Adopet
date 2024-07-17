package br.com.alura.challenge.adopet.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastroAbrigo(
        @NotBlank(message = "Nome não pode ser em branco ou nulo")
        String nome,

        @NotBlank(message = "login não pode ser em branco ou nulo")
        @Email(message = "Formato de login invalido")
        String login,

        @NotBlank(message = "senha não pode ser em branco ou nulo")
        String senha,

        @NotBlank(message = "cnpj não pode estar em branco")
        @Pattern(regexp = "\\d{14}", message = "cnpj deve ter 14 digitos")
        String cnpj,

        @NotBlank(message = "telefone não pode estar em branco")
        @Pattern(regexp = "\\d{8,11}")
        String telefone,

        @NotBlank(message = "cep não pode estar em branco")
        @Pattern(regexp = "\\d{8}", message = "formato de cep invalido")
        String cep,

        Long numero

) {
}
