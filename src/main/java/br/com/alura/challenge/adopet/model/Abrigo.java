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
    @Setter
    private String telefone;
    @Setter
    @Embedded
    private Endereco endereco;
    @OneToOne()
    @JoinColumn(name = "users_id")
    @Setter
    private User user;

    public Abrigo(String nome, String cnpj, String telefone, Endereco endereco) {
        this.nome = nome;
        this.cnpj = cnpj;

        this.telefone = telefone;
        this.endereco = endereco;
    }

}
