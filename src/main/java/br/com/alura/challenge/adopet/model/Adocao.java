package br.com.alura.challenge.adopet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "adocoes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Adocao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "animal")
    private Pet pet;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tutor")
    private Tutor tutor;
    @Column(name = "data")
    private LocalDateTime date = LocalDateTime.now();

    public Adocao(Pet pet, Tutor tutor) {
        this.pet = pet;
        this.tutor = tutor;
    }
}
