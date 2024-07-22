package br.com.alura.challenge.adopet.repository;

import br.com.alura.challenge.adopet.model.Adocao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdocaoRepository extends JpaRepository<Adocao, UUID> {
}
