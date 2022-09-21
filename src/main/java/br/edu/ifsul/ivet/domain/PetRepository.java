package br.edu.ifsul.ivet.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PetRepository extends CrudRepository<Pet, Long>{


    List<Pet> findByTipo(String tipo);
}
