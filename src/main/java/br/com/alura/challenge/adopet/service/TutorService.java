package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.dto.CadastroTutor;
import br.com.alura.challenge.adopet.dto.DadosRetornoTutor;
import br.com.alura.challenge.adopet.model.Tutor;
import br.com.alura.challenge.adopet.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;

    public Tutor cadastrarTutor(CadastroTutor dto){
        Tutor tutor = new Tutor(dto.login(), dto.password(), dto.nome() , dto.cidade(), dto.telefone(), dto.sobreMim());
        return repository.save(tutor);
    }

    public Page<DadosRetornoTutor> findAll(Pageable pageable){
        return repository.findAll(pageable)
                .map(DadosRetornoTutor::new);
    }
}
