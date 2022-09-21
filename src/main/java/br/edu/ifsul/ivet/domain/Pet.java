package br.edu.ifsul.ivet.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;

@Entity //Faz mapear a table pet para a classe carro, como tem o mesmo nome, esta tudo certo, se não olhar aula25 seção 5
public class Pet {
    @Id // inica q é campo de chave primaria da tabela.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Faz com que quando salvar um novo pet, um auto incremento no id
    private Long id;
    private  String nome;

    public Pet(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Pet() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
