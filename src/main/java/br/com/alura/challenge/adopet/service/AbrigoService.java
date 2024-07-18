package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.dto.abrigo.CadastroAbrigo;
import br.com.alura.challenge.adopet.dto.abrigo.DadosAtualizaAbrigo;
import br.com.alura.challenge.adopet.dto.abrigo.DadosDetalhamentoAbrigo;
import br.com.alura.challenge.adopet.dto.abrigo.DadosRetornoAbrigo;
import br.com.alura.challenge.adopet.exception.EnderecoException;
import br.com.alura.challenge.adopet.model.Abrigo;
import br.com.alura.challenge.adopet.model.Endereco;
import br.com.alura.challenge.adopet.repository.AbrigoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    public Abrigo cadastrarAbrigo(CadastroAbrigo dto) {
        Endereco endereco = pegarEndereco(dto.cep());
        endereco.setNumero(dto.numero());
        Abrigo abrigo = new Abrigo(dto.nome(), dto.cnpj(), dto.login(), dto.senha(), dto.telefone(), endereco);
        return repository.save(abrigo);
    }

    private Endereco pegarEndereco(String cep) {
        cep = cep.replace("-", "");
        String uri = "http://viacep.com.br/ws/" + cep + "/json/";
        Endereco endereco = new RestTemplate().getForObject(uri, Endereco.class);
        if (endereco == null || endereco.getCep() == null){
            throw new EnderecoException("cep invalido");
        }
        return endereco;
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
            abrigo.setSenha(dto.senha());
        }
        if(dto.telefone() != null){
            abrigo.setTelefone(dto.telefone());
        }
        if(dto.cep() != null){
            abrigo.setEndereco(pegarEndereco(dto.cep()));
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

}
