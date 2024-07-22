package br.com.alura.challenge.adopet.repository;

import br.com.alura.challenge.adopet.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, UUID> {
    Page<Pet> findAllByAdotadoIsFalse(Pageable pageable);
}
