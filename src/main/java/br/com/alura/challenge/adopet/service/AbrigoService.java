package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.dto.CadastroAbrigo;
import br.com.alura.challenge.adopet.exception.EnderecoException;
import br.com.alura.challenge.adopet.model.Abrigo;
import br.com.alura.challenge.adopet.model.Endereco;
import br.com.alura.challenge.adopet.repository.AbrigoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

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
        cep = cep.replaceAll("-.", "");
        String uri = "http://viacep.com.br/ws/" + cep + "/json/";
        return new RestTemplate().getForObject(uri, Endereco.class);
    }
}
