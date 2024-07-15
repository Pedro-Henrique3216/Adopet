package br.com.alura.challenge.adopet.repository;

import br.com.alura.challenge.adopet.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TutorRepository extends JpaRepository<Tutor, UUID> {
}
