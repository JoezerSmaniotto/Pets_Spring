package br.edu.ifsul.ivet.domain.dto;

import br.edu.ifsul.ivet.domain.Pet;
import lombok.Data;

@Data
public class PetDTO {
    private Long id;
    private  String nome;
    private String tipo;

    public PetDTO(Pet p){
        this.id = p.getId();
        this.nome = p.getNome();
        this.tipo = p.getTipo();
    }
}
