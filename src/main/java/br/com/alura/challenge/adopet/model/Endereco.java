package br.com.alura.challenge.adopet.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Embeddable
public class Endereco {

    private String cep;
    private String logradouro;
    @Setter
    private Long numero;
    private String bairro;
    @JsonAlias("localidade")
    private String cidade;
    private String uf;

}
