package br.com.alura.challenge.adopet.dto.abrigo;

import br.com.alura.challenge.adopet.model.Abrigo;
import br.com.alura.challenge.adopet.model.Endereco;

import java.util.UUID;

public record DadosDetalhamentoAbrigo(
        UUID id,
        String nome,
        String cnpj,
        String login,
        String telefone,
        Endereco endereco
) {
    public DadosDetalhamentoAbrigo(Abrigo abrigo){
        this(abrigo.getId(), abrigo.getNome(), abrigo.getCnpj(), abrigo.getUser().getUsername(), abrigo.getTelefone(), abrigo.getEndereco());
    }
}
