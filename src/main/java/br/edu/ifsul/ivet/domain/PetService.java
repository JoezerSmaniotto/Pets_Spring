package br.edu.ifsul.ivet.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Irá pegar os dados do banco de dados futuramente
@Service
public class PetService {
    @Autowired
    private PetRepository rep; // Se Comunica com o banco de dados a interface PetRepository

    public Iterable<Pet>  getPets(){
        return rep.findAll();
    }

    public Optional<Pet> getPetById(Long id) {
        return rep.findById(id);
    }

    public List<Pet> getPetsByTipo(String tipo) {
        return rep.findByTipo(tipo);
    }


    public List<Pet>  getPetsFake(){
        List<Pet> pets = new ArrayList<>();

        pets.add(new Pet(1L , "Samanta"));
        pets.add(new Pet(1L , "Bigo"));
        pets.add(new Pet(1L , "Bolinha"));
        return pets;
    }

    public Pet insert(Pet pet) {
        Assert.isNull(pet.getId(), "Não foi possível atualizar o registro");
        return  rep.save(pet);
    }

    public Pet update(Pet pet, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro!");

        // Busca o pet no banco de dados
        Optional<Pet> optional = getPetById(id);
        if(optional.isPresent()) { // Verifica se existe o Pet de acordo com o Id informado.
            Pet db = optional.get();
            // Copiar as propriedades
            db.setNome(pet.getNome());
            db.setTipo(pet.getTipo());
            System.out.println("Pet id " + db.getId());

            // Atualiza o pet
            rep.save(db);

            return db;
        } else {
            //return null;
            throw new RuntimeException("Não foi possível atualizar o registro do Pet!");
        }
    }

    public void delete(Long id) {
        Optional<Pet> pet = getPetById(id);
        if(pet.isPresent()) { // Só delete o pet existe aquele ID passado.
            rep.deleteById(id);
        }
    }
}
