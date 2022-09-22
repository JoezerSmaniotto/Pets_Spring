package br.edu.ifsul.ivet.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;


@Entity //Faz mapear a table pet para a classe carro, como tem o mesmo nome, esta tudo certo, se não olhar aula25 seção 5
@Data
public class Pet {
    @Id // inica q é campo de chave primaria da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Faz com que quando salvar um novo pet, um auto incremento no id
    private Long id;
    private  String nome;
    private String tipo;


}
