package br.edu.ifsul.ivet;

import br.edu.ifsul.ivet.api.pets.Pet;
import br.edu.ifsul.ivet.api.pets.PetDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = IvetApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PetsControllerTest extends BaseAPITest {

    //@Autowired
    //protected TestRestTemplate rest;

    //@Autowired
    //private PetService service;


    private ResponseEntity<PetDTO> getPet(String url) {
        setupTest();
        return get(url, PetDTO.class);
    }

    private ResponseEntity<List<PetDTO>>getPets(String url) {
        setupTest();
        HttpHeaders headers = getHeaders();

        return rest.exchange(
                url,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                new ParameterizedTypeReference<List<PetDTO>>() {
                });
    }


    @Test
    public void testSave() {
        setupTest();
        Pet pet = new Pet();
        pet.setNome("NewPet");
        pet.setTipo("cao");

        // Insert
        ResponseEntity response = post("/api/v1/pets", pet, null);
        System.out.println(response);

        // Verifica se criou
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Buscar o objeto
        String location = response.getHeaders().get("location").get(0);
        PetDTO c = getPet(location).getBody();

        assertNotNull(c);
        assertEquals("NewPet", c.getNome());
        assertEquals("cao", c.getTipo());

        // Deletar o objeto
        delete(location, null);

        // Verificar se deletou
        assertEquals(HttpStatus.NOT_FOUND, getPet(location).getStatusCode());
    }

    @Test
    public void testLista() { //OK
        setupTest();
        List<PetDTO> pets = getPets("/api/v1/pets").getBody();
        assertNotNull(pets);
        assertEquals(6, pets.size());
    }

    @Test
    public void testListaPorTipo() { //OK
        setupTest();
        assertEquals(3, getPets("/api/v1/pets/tipo/cao").getBody().size());
        assertEquals(3, getPets("/api/v1/pets/tipo/gato").getBody().size());
        assertEquals(HttpStatus.NO_CONTENT, getPets("/api/v1/pets/tipo/xxx").getStatusCode());
    }

    @Test
    public void testGetOk() {//ok
        setupTest();
        ResponseEntity<PetDTO> response = getPet("/api/v1/pets/1");
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        PetDTO p = response.getBody();
        assertNotNull(p);
        assertEquals("Onix", p.getNome());
    }

    @Test
    public void testGetNotFound() { //ok
        setupTest();
        ResponseEntity response = getPet("/api/v1/pets/1100");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
