package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.dto.CadastroTutor;
import br.com.alura.challenge.adopet.model.Tutor;
import br.com.alura.challenge.adopet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public Tutor cadastrarTutor(CadastroTutor dto){
        Tutor tutor = new Tutor(dto.login(), dto.password(), dto.nome() , dto.cidade(), dto.telefone(), dto.sobreMim());
        return repository.save(tutor);
    }
}
