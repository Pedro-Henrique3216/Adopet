package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.dto.abrigo.CadastroAbrigo;
import br.com.alura.challenge.adopet.dto.abrigo.DadosAtualizaAbrigo;
import br.com.alura.challenge.adopet.dto.abrigo.DadosDetalhamentoAbrigo;
import br.com.alura.challenge.adopet.dto.abrigo.DadosRetornoAbrigo;
import br.com.alura.challenge.adopet.dto.pet.CadastroPets;
import br.com.alura.challenge.adopet.dto.pet.DadosDetalhamentoPet;
import br.com.alura.challenge.adopet.model.Abrigo;
import br.com.alura.challenge.adopet.model.Endereco;
import br.com.alura.challenge.adopet.model.User;
import br.com.alura.challenge.adopet.model.UserRole;
import br.com.alura.challenge.adopet.repository.AbrigoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AbrigoService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private PetService petService;

    @Autowired
    private UserService userService;

    public Abrigo cadastrarAbrigo(CadastroAbrigo dto) {

        Endereco endereco = BuscaEndereco.pegarEndereco(dto.cep());
        endereco.setNumero(dto.numero());

        Abrigo abrigo = new Abrigo(dto.nome(), dto.cnpj(), dto.telefone(), endereco);

        User user = new User(dto.login(), encoder.encode(dto.senha()), UserRole.ABRIGO);
        userService.saveUser(user);

        abrigo.setUser(user);
        return repository.save(abrigo);
    }

    public Page<DadosRetornoAbrigo> listarAbrigos(Pageable pageable) {
        Page<Abrigo> abrigos = repository.findAll(pageable);
        if(abrigos.isEmpty()){
            throw new EntityNotFoundException("Não existe um abrigo cadastrado");
        }
        return abrigos
                .map(DadosRetornoAbrigo::new);

    }

    public DadosDetalhamentoAbrigo buscarAbrigoPorId(UUID id){
        Abrigo abrigo = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("abrigo não encontrado"));
        return new DadosDetalhamentoAbrigo(abrigo);
    }

    public DadosDetalhamentoAbrigo atualizarAbrigo(UUID id, DadosAtualizaAbrigo dto) {
        Abrigo abrigo = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("abrigo não encontrado"));
        alteraCampos(abrigo, dto);
        return new DadosDetalhamentoAbrigo(abrigo);
    }

    private void alteraCampos(Abrigo abrigo, DadosAtualizaAbrigo dto) {
        if(dto.nome() != null){
            abrigo.setNome(dto.nome());
        }
        if(dto.senha() != null){
            abrigo.getUser().setPassword(encoder.encode(dto.senha()));
        }
        if(dto.telefone() != null){
            abrigo.setTelefone(dto.telefone());
        }
        if(dto.cep() != null){
            abrigo.setEndereco(BuscaEndereco.pegarEndereco(dto.cep()));
        }
        if(dto.numero() != null){
            abrigo.getEndereco().setNumero(dto.numero());
        }
    }

    public void excluir(UUID id) {
        if(repository.findById(id).isEmpty()){
            throw new EntityNotFoundException("Não existe abrigo cadastrado com esse id");
        }
        repository.deleteById(id);
    }

    public DadosDetalhamentoPet cadastraPet(CadastroPets dto) {
        Abrigo abrigo = repository.findById(dto.abrigoId()).orElseThrow(() -> new EntityNotFoundException("abrigo não encontrado"));
        return petService.cadastrarAbrigo(dto, abrigo);
    }
}
