package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.dto.CadastroAbrigo;
import br.com.alura.challenge.adopet.dto.DadosDetalhamentoAbrigo;
import br.com.alura.challenge.adopet.exception.EnderecoException;
import br.com.alura.challenge.adopet.model.Abrigo;
import br.com.alura.challenge.adopet.model.Endereco;
import br.com.alura.challenge.adopet.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    public DadosDetalhamentoAbrigo cadastrarAbrigo(CadastroAbrigo dto) {
        Endereco endereco = pegarEndereco(dto.cep());
        if(endereco.getCep() == null) {
            throw new EnderecoException("cep invalido");
        }
        endereco.setNumero(dto.numero());
        Abrigo abrigo = new Abrigo(dto.nome(), dto.cnpj(), dto.login(), dto.senha(), dto.telefone(), endereco);
        repository.save(abrigo);
        return new DadosDetalhamentoAbrigo(abrigo.getId(), abrigo.getNome(), abrigo.getCnpj(), abrigo.getLogin(), abrigo.getTelefone(), abrigo.getEndereco());
    }

    private Endereco pegarEndereco(String cep) {
        cep = cep.replace("-", "");
        String uri = "http://viacep.com.br/ws/" + cep + "/json/";
        return new RestTemplate().getForObject(uri, Endereco.class);
    }
}
