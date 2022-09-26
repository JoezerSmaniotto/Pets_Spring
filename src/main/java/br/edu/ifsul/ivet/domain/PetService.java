package br.edu.ifsul.ivet.domain;

import br.edu.ifsul.ivet.api.exception.ObjectNotFoundException;
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

    public PetDTO getPetById(Long id) { // Sintaxe Resumida  = Sintaxe não resumida
        Optional<Pet> carro = rep.findById(id);
        return carro.map(PetDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado"));

    }

    public List<PetDTO> getPetsByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(PetDTO::create).collect(Collectors.toList());
    }


    /*public List<Pet>  getPetsFake(){
        List<Pet> pets = new ArrayList<>();

        pets.add(new Pet(1L , "Samanta"));
        pets.add(new Pet(1L , "Bigo"));
        pets.add(new Pet(1L , "Bolinha"));
        return pets;
    }*/

    public PetDTO insert(Pet pet) {
        Assert.isNull(pet.getId(), "Não foi possível atualizar o registro");
        return  PetDTO.create(rep.save(pet));
    }

    public PetDTO update(Pet pet, Long id) {
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

            return PetDTO.create(db);// Com o converto o Obj do tipo Pet para PetDTO com o PetDTO.create
        } else {
            return null;
            //throw new RuntimeException("Não foi possível atualizar o registro do Pet!");
        }
    }

    public void delete(Long id) {
        rep.deleteById(id);
    }
}
