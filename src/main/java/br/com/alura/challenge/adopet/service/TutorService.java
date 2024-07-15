package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.dto.CadastroTutor;
import br.com.alura.challenge.adopet.dto.DadosRetornoTutor;
import br.com.alura.challenge.adopet.model.Tutor;
import br.com.alura.challenge.adopet.repository.TutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public Tutor cadastrarTutor(CadastroTutor dto){
        Tutor tutor = new Tutor(dto.login(), dto.password(), dto.nome() , dto.cidade(), dto.telefone(), dto.sobreMim());
        return repository.save(tutor);
    }

    public Page<DadosRetornoTutor> findAll(Pageable pageable){
        if(repository.findAll(pageable).isEmpty()){
            throw new EntityNotFoundException("Não existe tutor cadastrado");
        }
        return repository.findAll(pageable)
                .map(DadosRetornoTutor::new);
    }

    public DadosRetornoTutor findById(UUID id){
        Tutor tutor = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe usuario com esse id"));
        return new DadosRetornoTutor(tutor);
    }
}
