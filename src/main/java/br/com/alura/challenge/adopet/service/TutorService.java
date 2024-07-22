package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.dto.tutor.CadastroTutor;
import br.com.alura.challenge.adopet.dto.tutor.DadosAtualizaTutor;
import br.com.alura.challenge.adopet.dto.tutor.DadosRetornoTutor;
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

    public DadosRetornoTutor atualizar(UUID id, DadosAtualizaTutor dto){
        Tutor tutor = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe usuario com esse id"));
        if(dto.nome() != null){
            tutor.setNome(dto.nome());
        }
        if(dto.cidade() != null){
            tutor.setCidade(dto.cidade());
        }
        if(dto.telefone() != null){
            tutor.setTelefone(dto.telefone());
        }
        if(dto.sobreMim() != null){
            tutor.setSobreMim(dto.sobreMim());
        }
        if(dto.password() != null){
            tutor.setPassword(dto.password());
        }
        return new DadosRetornoTutor(tutor);
    }

    public void excluir(UUID id){
        if(repository.findById(id).isEmpty()){
            throw new EntityNotFoundException("Não existe tutor cadastrado com esse id");
        }
        repository.deleteById(id);
    }

    public Tutor buscarTutor(UUID id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não existe usuario com esse id"));
    }
}
