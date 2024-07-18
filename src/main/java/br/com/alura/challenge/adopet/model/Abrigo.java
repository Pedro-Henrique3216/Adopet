package br.com.alura.challenge.adopet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "abrigos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Abrigo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Setter
    private String nome;
    private String cnpj;
    private String login;
    @Setter
    private String senha;
    @Setter
    private String telefone;
    @Setter
    @Embedded
    private Endereco endereco;

    public Abrigo(String nome, String cnpj, String login, String senha, String telefone, Endereco endereco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.login = login;
        this.senha = senha;
        this.telefone = telefone;
        this.endereco = endereco;
    }

}
