package br.edu.ifsul.ivet.domain;

import br.edu.ifsul.ivet.domain.dto.PetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// Irá pegar os dados do banco de dados futuramente
@Service
public class PetService {
    @Autowired
    private PetRepository rep; // Se Comunica com o banco de dados a interface PetRepository

    public List<PetDTO>  getPets(){
        // FindAll retorna lista de pets
        // chamamos o strem para maperiar a lista
        // Percoro pet a pet criando um petDTO
        // Por fim gera uma nova lista de petDTO
        List<PetDTO> list = rep.findAll().stream().map(PetDTO::create).collect(Collectors.toList());
        return list;
       // return rep.findAll().stream().map(PetDTO::create).collect(Collectors.toList());
    }

    public Optional<PetDTO> getPetById(Long id) { // Sintaxe Resumida  = Sintaxe não resumida
        return rep.findById(id).map(PetDTO::create); // map(PetDTO::new); == map(p -> new PetDTO(p);
        // o findByTipo sempre retorna uma Optional, mas como quero converter para um DTO, caso a conversão com map para DTO que quero, no caso de PetDTO
        // Se existe aquele ID ele converte, se não ele segue sendo um DTO

        //Optional<Pet> pet = rep.findById(id);
        // return pet.map(p -> Optional.of(new PetDTO(p))).orElse(null);

    }

    public List<PetDTO> getPetsByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(PetDTO::q).collect(Collectors.toList());
    }


    /*public List<Pet>  getPetsFake(){
        List<Pet> pets = new ArrayList<>();

        pets.add(new Pet(1L , "Samanta"));
        pets.add(new Pet(1L , "Bigo"));
        pets.add(new Pet(1L , "Bolinha"));
        return pets;
    }*/

    public Pet insert(Pet pet) {
        Assert.isNull(pet.getId(), "Não foi possível atualizar o registro");
        return  rep.save(pet);
    }

    public Pet update(Pet pet, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro!");

        // Busca o pet no banco de dados
        Optional<Pet> optional = rep.findById(id);
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

    public boolean delete(Long id) {
        if(getPetById(id).isPresent()) { // Só delete o pet existe aquele ID passado.
            rep.deleteById(id);
            return true;
        }
        return false;
    }
}
