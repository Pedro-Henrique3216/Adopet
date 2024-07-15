package br.com.alura.challenge.adopet.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CadastroTutor(
        @NotBlank(message = "Nome não pode ser em branco ou nulo")
        String nome,

        @NotBlank(message = "login não pode ser em branco ou nulo")
        @Email(message = "Formato de login invalido")
        String login,

        @NotBlank(message = "senha não pode ser em branco ou nulo")
        String password,

        @NotBlank(message = "cidade não pode ser em branco ou nulo")
        String cidade,

        String telefone,

        @JsonAlias("sobre_mim")
        @NotBlank(message = "sobre mim não pode ser em branco ou nulo")
        String sobreMim
) {
}
