package br.edu.ifsul.ivet;

import br.edu.ifsul.ivet.api.pets.Pet;
import br.edu.ifsul.ivet.api.pets.PetService;
import br.edu.ifsul.ivet.api.pets.PetDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = IvetApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PetsAPITest {

    @Autowired
    protected TestRestTemplate rest;

    @Autowired
    private PetService service;

    private ResponseEntity<PetDTO> getPet(String url) {
        return rest.getForEntity(url, PetDTO.class);
    }

    private ResponseEntity<List<PetDTO>>getPets(String url) {
        return rest.withBasicAuth("user","123").exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PetDTO>>() {
                });
    }


    @Test
    public void testSave() {

        Pet pet = new Pet();
        pet.setNome("NewPet");
        pet.setTipo("cao");

        // Insert
        ResponseEntity response = rest.postForEntity("/api/v1/pets", pet, null);
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
        rest.delete(location);

        // Verificar se deletou
        assertEquals(HttpStatus.NOT_FOUND, getPet(location).getStatusCode());
    }

    @Test
    public void testLista() { //OK
        List<PetDTO> pets = getPets("/api/v1/pets").getBody();
        assertNotNull(pets);
        assertEquals(6, pets.size());
    }

    @Test
    public void testListaPorTipo() { //OK

        assertEquals(3, getPets("/api/v1/pets/tipo/cao").getBody().size());
        assertEquals(3, getPets("/api/v1/pets/tipo/gato").getBody().size());


        assertEquals(HttpStatus.NO_CONTENT, getPets("/api/v1/pets/tipo/xxx").getStatusCode());
    }

    @Test
    public void testGetOk() {//ok

        ResponseEntity<PetDTO> response = getPet("/api/v1/pets/1");
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        PetDTO p = response.getBody();
        assertEquals("Onix", p.getNome());
    }

    @Test
    public void testGetNotFound() { //ok

        ResponseEntity response = getPet("/api/v1/pets/1100");
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
