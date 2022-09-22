package br.edu.ifsul.ivet.api;

import br.edu.ifsul.ivet.domain.Pet;
import br.edu.ifsul.ivet.domain.PetService;
import br.edu.ifsul.ivet.domain.dto.PetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // converter essa classe em um web service.
@RequestMapping("/api/v1/pets") // O RequestMapping mapeia o  web service, neste caso para o  "/" raiz do projeto.
public class PetsController {
    @Autowired
    private PetService service;

    @GetMapping()
    public ResponseEntity get(){
        return ResponseEntity.ok(service.getPets()) ;// ResponseEntity.ok é statuCode 200
    }

    @GetMapping("/{id}")// usa o "/" do mapeamento acima por default ao chamar essa página irá chamar o GetMapping, isso acontce pq apliquei o @GetMapping, neste caso ele herda ("/") feito no RequestMapping se eu não passar nada.
    public ResponseEntity get(@PathVariable("id") Long id){
        Optional<Pet> pet = service.getPetById(id);
        //Lambda
        return pet.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
                       // Se Existe fazer  | Não existe retorna notFound

        //return pet.isPresent() ?
                //ResponseEntity.ok(pet.get()) : // o pet.get() é Pet que esta dentro do optional Seria assim => Pet p = pet.get();
                //ResponseEntity.notFound().build();
    }

    @GetMapping("/tipo/{tipo}")// usa o "/" do mapeamento acima por default ao chamar essa página irá chamar o GetMapping, isso acontce pq apliquei o @GetMapping, neste caso ele herda ("/") feito no RequestMapping se eu não passar nada.
    public ResponseEntity getPetsByTipo(@PathVariable("tipo") String tipo){
        List<PetDTO>  pets = service.getPetsByTipo(tipo);
        return pets.isEmpty() ?
                ResponseEntity.noContent().build() : // O noContent 204
                ResponseEntity.ok(pets); // Ok é 200
    }

    @PostMapping
    public String post(@RequestBody Pet pet){
        Pet p = service.insert(pet);
        return "Pet salvo com sucesso, ID: " + p.getId();
    }

    @PutMapping("/{id}")
    public String put(@PathVariable("id") Long id, @RequestBody Pet pet){
        Pet p = service.update(pet, id);
        return "Pet atualizado com sucesso, ID: " +  p.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "Pet deletado com sucesso!";
    }


    /*@GetMapping()
    public ResponseEntity<String> getIndex() {
        return ResponseEntity.ok("Consulte a documentação da API.");
    }*/

}
