package br.edu.ifsul.ivet.api;

import br.edu.ifsul.ivet.domain.Pet;
import br.edu.ifsul.ivet.domain.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // converter essa classe em um web service.
@RequestMapping("/api/v1/pets") // O RequestMapping mapeia o  web service, neste caso para o  "/" raiz do projeto.
public class PetsController {
    @Autowired
    private PetService service;

    @GetMapping()
    public Iterable<Pet> get(){
        return service.getPets();
    }

    @GetMapping("/{id}")// usa o "/" do mapeamento acima por default ao chamar essa página irá chamar o GetMapping, isso acontce pq apliquei o @GetMapping, neste caso ele herda ("/") feito no RequestMapping se eu não passar nada.
    public Optional<Pet> get(@PathVariable("id") Long id){

        return service.getPetById(id);
    }

    @GetMapping("/tipo/{tipo}")// usa o "/" do mapeamento acima por default ao chamar essa página irá chamar o GetMapping, isso acontce pq apliquei o @GetMapping, neste caso ele herda ("/") feito no RequestMapping se eu não passar nada.
    public Iterable<Pet> getPetsByTipo(@PathVariable("tipo") String tipo){
        return service.getPetsByTipo(tipo);
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
