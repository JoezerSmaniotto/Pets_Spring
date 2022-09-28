package br.edu.ifsul.ivet.api.pets;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
//  PetRepository Irá de comunicar com o banco de dados
public interface PetRepository extends JpaRepository<Pet, Long> {


    List<Pet> findByTipo(String tipo);
    List<Pet> findByNomeContaining(String query);
}
