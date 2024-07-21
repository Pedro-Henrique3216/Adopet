package br.com.alura.challenge.adopet.dto.pet;

public record DadosAtualizaPet(
        String nome,
        String descricao,
        String idade,
        String imagem,
        String cep,
        Long numero
) {
}
