package br.com.alura.challenge.adopet.repository;

import br.com.alura.challenge.adopet.model.Abrigo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AbrigoRepository extends JpaRepository<Abrigo, UUID> {
}
