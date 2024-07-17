package br.com.alura.challenge.adopet.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CadastroAbrigo(
        @NotBlank(message = "Nome não pode ser em branco ou nulo")
        String nome,

        @NotBlank(message = "login não pode ser em branco ou nulo")
        @Email(message = "Formato de login invalido")
        String login,

        @NotBlank(message = "senha não pode ser em branco ou nulo")
        String senha,

        String cnpj,

        String telefone,

        String cep,
        Long numero

) {
}
