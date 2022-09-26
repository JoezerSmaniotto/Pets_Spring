package br.edu.ifsul.ivet.api.pets;

import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class PetDTO {
    private Long id;
    private String nome;
    private String descricao;
    private String raca;
    private String urlFoto;
    private String urlVideo;
    private String latitude;
    private String longitude;
    private String tipo;



    /*public PetDTO(Pet p){ // APAGAR ESTE QUANDO ADICIONAR O  ModelMapper
        this.id = p.getId();
        this.nome = p.getNome();
        this.tipo = p.getTipo();
    }*/

    public static PetDTO create(Pet p){
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(p, PetDTO.class);
    }

}
