package br.edu.ifsul.ivet.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Irá pegar os dados do banco de dados futuramente
@Service
public class PetService {
    @Autowired
    private PetRepository rep;

    public Iterable<Pet>  getPets(){
        return rep.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return rep.findById(id);
    }

    public Iterable<Pet> getPetsByTipo(String tipo) {
        return rep.findByTipo(tipo);
    }

    public List<Pet>  getPetsFake(){
        List<Pet> pets = new ArrayList<>();

        pets.add(new Pet(1L , "Samanta"));
        pets.add(new Pet(1L , "Bigo"));
        pets.add(new Pet(1L , "Bolinha"));
        return pets;
    }



}
