package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.dto.pet.CadastroPets;
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
}
