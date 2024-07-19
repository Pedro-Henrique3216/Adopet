package br.com.alura.challenge.adopet.dto.pet;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

public record CadastroPets(

        @JsonAlias("abrigo_id")
        @NotNull(message = "abrigo id não pode ser null")
        UUID abrigoId,

        @NotBlank(message = "Nome não pode ser em branco ou nulo")
        String nome,

        @NotBlank(message = "descrição não pode ser em branco ou nulo")
        String descricao,

        @NotNull(message = "adotado não pode ser nulo")
        Boolean adotado,

        @NotBlank(message = "idade não pode ser em branco ou nulo")
        String idade,

        @NotBlank(message = "cep não pode estar em branco")
        @Pattern(regexp = "\\d{8}", message = "formato de cep invalido")
        String cep,

        Long numero,

        @URL(message = "url invalida")
        String imagem
) {
}
