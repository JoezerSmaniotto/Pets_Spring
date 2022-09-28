package br.edu.ifsul.ivet.api.pets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

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
        PetDTO pet = service.getPetById(id);
        return ResponseEntity.ok(pet);
    }

    @GetMapping("/tipo/{tipo}")// usa o "/" do mapeamento acima por default ao chamar essa página irá chamar o GetMapping, isso acontce pq apliquei o @GetMapping, neste caso ele herda ("/") feito no RequestMapping se eu não passar nada.
    public ResponseEntity getPetsByTipo(@PathVariable("tipo") String tipo){
        List<PetDTO>  pets = service.getPetsByTipo(tipo);
        return pets.isEmpty() ?
                ResponseEntity.noContent().build() : // O noContent 204
                ResponseEntity.ok(pets); // Ok é 200
    }

    @PostMapping
    @Secured({"ROLE_ADMIN"})
    public ResponseEntity post(@RequestBody Pet pet){

        PetDTO p = service.insert(pet);
        URI location = getUri(p.getId());
        return ResponseEntity.created(location).build();
    }

    private URI getUri(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Pet pet){
        PetDTO p = service.update(pet, id);
        return p != null ?
                ResponseEntity.ok(p) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build(); // Ok é 200

    }

    @GetMapping("/search")
    public ResponseEntity search(@RequestParam("query") String query) {
        List<PetDTO> carros = service.search(query);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }


    /*@GetMapping()
    public ResponseEntity<String> getIndex() {
        return ResponseEntity.ok("Consulte a documentação da API.");
    }*/

}
