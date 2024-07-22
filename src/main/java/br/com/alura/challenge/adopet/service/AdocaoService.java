package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.dto.adocao.CadastroAdocao;
import br.com.alura.challenge.adopet.dto.adocao.DadosDetalhamentoAdocao;
import br.com.alura.challenge.adopet.model.Adocao;
import br.com.alura.challenge.adopet.model.Pet;
import br.com.alura.challenge.adopet.model.Tutor;
import br.com.alura.challenge.adopet.repository.AdocaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdocaoService {

    @Autowired
    private AdocaoRepository repository;

    @Autowired
    private TutorService tutorService;

    @Autowired
    private PetService petService;

    public Adocao createAdocao(CadastroAdocao dto) {
        Tutor tutor = tutorService.buscarTutor(dto.tutor());
        Pet pet = petService.buscarPet(dto.animal());
        Adocao adocao = new Adocao(pet, tutor);
        pet.setAdotado(Boolean.TRUE);
        return repository.save(adocao);
    }

    public void excluirAdocao(UUID id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Não existe uma adocao com esse id");
        }
        Adocao adocao = repository.getReferenceById(id);
        Pet pet = adocao.getPet();
        pet.setAdotado(Boolean.FALSE);
        repository.deleteById(id);
    }
}
