package br.com.alura.challenge.adopet.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tutores")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Tutor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String nome;
    private String cidade;
    private String telefone;
    private String sobreMim;
    @OneToOne
    @JoinColumn(name = "users_id")
    @Setter
    private User user;

    public Tutor(String nome, String cidade, String telefone, String sobreMim) {
        this.nome = nome;
        this.cidade = cidade;
        this.telefone = telefone;
        this.sobreMim = sobreMim;
    }
}
