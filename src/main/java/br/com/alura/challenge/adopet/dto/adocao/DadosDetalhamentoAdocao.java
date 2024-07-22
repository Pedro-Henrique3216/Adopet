package br.com.alura.challenge.adopet.dto.adocao;

import br.com.alura.challenge.adopet.model.Abrigo;
import br.com.alura.challenge.adopet.model.Adocao;
import br.com.alura.challenge.adopet.model.Endereco;

import java.util.UUID;

public record DadosDetalhamentoAdocao(
        UUID id,
        UUID animal,
        UUID tutor
) {
    public DadosDetalhamentoAdocao(Adocao adocao){
        this(adocao.getId(), adocao.getPet().getId(), adocao.getTutor().getId());
    }
}
