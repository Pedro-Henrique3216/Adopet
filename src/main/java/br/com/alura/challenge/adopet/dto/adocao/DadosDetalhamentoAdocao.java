package br.com.alura.challenge.adopet.dto.adocao;

import br.com.alura.challenge.adopet.model.Abrigo;
import br.com.alura.challenge.adopet.model.Adocao;
import br.com.alura.challenge.adopet.model.Endereco;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public record DadosDetalhamentoAdocao(
        UUID id,
        UUID animal,
        UUID tutor,
        String data
) {
    public DadosDetalhamentoAdocao(Adocao adocao){
        this(adocao.getId(), adocao.getPet().getId(), adocao.getTutor().getId(), adocao.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
