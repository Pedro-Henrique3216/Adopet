package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.dto.abrigo.DadosAtualizaAbrigo;
import br.com.alura.challenge.adopet.dto.pet.CadastroPets;
import br.com.alura.challenge.adopet.dto.pet.DadosAtualizaPet;
import br.com.alura.challenge.adopet.dto.pet.DadosDetalhamentoPet;
import br.com.alura.challenge.adopet.model.Abrigo;
import br.com.alura.challenge.adopet.model.Endereco;
import br.com.alura.challenge.adopet.model.Pet;
import br.com.alura.challenge.adopet.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    public DadosDetalhamentoPet cadastrarAbrigo(CadastroPets dto, Abrigo abrigo) {
        Endereco endereco = BuscaEndereco.pegarEndereco(dto.cep());
        endereco.setNumero(dto.numero());
        Pet pet = new Pet(abrigo, dto.nome(), dto.descricao(), dto.idade(), dto.imagem(), endereco);
        repository.save(pet);
        return new DadosDetalhamentoPet(pet);
    }

    public Page<DadosDetalhamentoPet> listarTodos(Pageable pageable) {
        Page<DadosDetalhamentoPet> pet = repository.findAll(pageable)
                .map(DadosDetalhamentoPet::new);
        if(pet.isEmpty()){
            throw new EntityNotFoundException("Não existe pet cadastrado");
        }
        return pet;
    }

    public DadosDetalhamentoPet buscarPorId(UUID id) {
        Pet pet = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("id não encontrado"));
        return new DadosDetalhamentoPet(pet);
    }

    public DadosDetalhamentoPet atualizaPet(UUID id, DadosAtualizaPet dto) {
        Pet pet = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("id não encontrado"));
        alteraCampos(pet, dto);
        return new DadosDetalhamentoPet(pet);
    }

    private void alteraCampos(Pet pet, DadosAtualizaPet dto) {
        if(dto.nome() != null){
            pet.setNome(dto.nome());
        }
        if(dto.descricao() != null){
            pet.setDescricao(dto.descricao());
        }
        if(dto.idade() != null){
            pet.setIdade(dto.idade());
        }
        if(dto.imagem() != null){
            pet.setImagem(dto.imagem());
        }
        if(dto.cep() != null){
            pet.setEndereco(BuscaEndereco.pegarEndereco(dto.cep()));
        }
        if(dto.numero() != null){
            pet.getEndereco().setNumero(dto.numero());
        }
    }
}
