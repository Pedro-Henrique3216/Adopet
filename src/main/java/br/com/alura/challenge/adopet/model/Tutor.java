package br.com.alura.challenge.adopet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "tutores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String login;
    private String password;
    private String nome;
    private String cidade;
    private String telefone;
    private String sobreMim;

    public Tutor(String login, String password, String nome, String cidade, String telefone, String sobreMim) {
        this.login = login;
        this.password = password;
        this.nome = nome;
        this.cidade = cidade;
        this.telefone = telefone;
        this.sobreMim = sobreMim;
    }
}
