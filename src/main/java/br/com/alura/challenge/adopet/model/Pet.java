package br.com.alura.challenge.adopet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "pets")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String descricao;
    private Boolean adotado;
    private String idade;
    @Embedded
    private Endereco endereco;
    private String imagem;
    @ManyToOne()
    @JoinColumn(name = "abrigo_id")
    private Abrigo abrigo;

    public Pet(Abrigo abrigo, String nome, String descricao, String idade, String imagem, Endereco endereco) {
        this.abrigo = abrigo;
        this.nome = nome;
        this.descricao = descricao;
        this.adotado = false;
        this.idade = idade;
        this.imagem = imagem;
        this.endereco = endereco;
    }
}
