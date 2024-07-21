package br.com.alura.challenge.adopet.dto.pet;

import br.com.alura.challenge.adopet.model.Endereco;

public record DadosAtualizaPet(
        String nome,
        String descricao,
        String idade,
        String imagem,
        String cep,
        Long numero
) {
}
