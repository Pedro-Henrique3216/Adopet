package br.com.alura.challenge.adopet.dto.pet;

import br.com.alura.challenge.adopet.model.Pet;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record DadosDetalhamentoPet(

        UUID id,

        String nome,

        String descricao,

        Boolean adotado,

        String idade,

        String imagem,
        @JsonProperty("abrigo_id")
        UUID abrigoId
) {

    public DadosDetalhamentoPet(Pet pet){
        this(pet.getId(), pet.getNome(), pet.getDescricao(), pet.getAdotado(), pet.getIdade(), pet.getImagem(), pet.getAbrigo().getId());
    }
}
