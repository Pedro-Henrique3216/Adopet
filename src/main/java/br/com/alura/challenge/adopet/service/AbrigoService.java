package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.dto.CadastroAbrigo;
import br.com.alura.challenge.adopet.dto.DadosDetalhamentoAbrigo;
import br.com.alura.challenge.adopet.dto.DadosRetornoAbrigo;
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

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    public Abrigo cadastrarAbrigo(CadastroAbrigo dto) {
        Endereco endereco = pegarEndereco(dto.cep());
        if(endereco.getCep() == null) {
            throw new EnderecoException("cep invalido");
        }
        endereco.setNumero(dto.numero());
        Abrigo abrigo = new Abrigo(dto.nome(), dto.cnpj(), dto.login(), dto.senha(), dto.telefone(), endereco);
        return repository.save(abrigo);
    }

    private Endereco pegarEndereco(String cep) {
        cep = cep.replace("-", "");
        String uri = "http://viacep.com.br/ws/" + cep + "/json/";
        return new RestTemplate().getForObject(uri, Endereco.class);
    }

    public Page<DadosRetornoAbrigo> listarAbrigos(Pageable pageable) {
        Page<Abrigo> abrigos = repository.findAll(pageable);
        if(abrigos.isEmpty()){
            throw new EntityNotFoundException("Não existe um abrigo cadastrado");
        }
        return abrigos
                .map(DadosRetornoAbrigo::new);

    }
}
