package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.dto.adocao.CadastroAdocao;
import br.com.alura.challenge.adopet.dto.adocao.DadosDetalhamentoAdocao;
import br.com.alura.challenge.adopet.model.Adocao;
import br.com.alura.challenge.adopet.model.Pet;
import br.com.alura.challenge.adopet.model.Tutor;
import br.com.alura.challenge.adopet.repository.AdocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdocaoService {

    @Autowired
    private AdocaoRepository repository;

    @Autowired
    private TutorService tutorService;

    @Autowired
    private PetService petService;

    public DadosDetalhamentoAdocao createAdocao(CadastroAdocao dto) {
        Tutor tutor = tutorService.buscarTutor(dto.tutor());
        Pet pet = petService.buscarPet(dto.animal());
        Adocao adocao = new Adocao(pet, tutor);
        pet.setAdotado(Boolean.TRUE);
        repository.save(adocao);
        return new DadosDetalhamentoAdocao(adocao);
    }
}
