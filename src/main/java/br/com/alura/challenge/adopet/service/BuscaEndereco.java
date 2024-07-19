package br.com.alura.challenge.adopet.service;

import br.com.alura.challenge.adopet.exception.EnderecoException;
import br.com.alura.challenge.adopet.model.Endereco;
import org.springframework.web.client.RestTemplate;

public class BuscaEndereco {

    public static Endereco pegarEndereco(String cep) {
        cep = cep.replace("-", "");
        String uri = "http://viacep.com.br/ws/" + cep + "/json/";
        Endereco endereco = new RestTemplate().getForObject(uri, Endereco.class);
        if (endereco == null || endereco.getCep() == null){
            throw new EnderecoException("cep invalido");
        }
        return endereco;
    }
}
