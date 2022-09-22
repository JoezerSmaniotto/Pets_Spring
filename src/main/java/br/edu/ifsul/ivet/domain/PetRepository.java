package br.edu.ifsul.ivet.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
//  PetRepository Irá de comunicar com o banco de dados
public interface PetRepository extends JpaRepository<Pet, Long> {


    List<Pet> findByTipo(String tipo);
}
