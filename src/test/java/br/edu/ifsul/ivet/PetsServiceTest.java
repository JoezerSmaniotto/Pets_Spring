package br.edu.ifsul.ivet;

import br.edu.ifsul.ivet.api.infra.exception.ObjectNotFoundException;
import br.edu.ifsul.ivet.api.pets.Pet;
import br.edu.ifsul.ivet.api.pets.PetService;
import br.edu.ifsul.ivet.api.pets.PetDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.*;


@RunWith(SpringRunner.class)
@SpringBootTest
class PetsServiceTest {

    @Autowired
    private PetService service;
    @Test
    public void testSave() {
        Pet pet = new Pet();
        pet.setNome("Trovao");
        pet.setTipo("cao");

        PetDTO p = service.insert(pet);// Insere um pet

        assertNotNull(p);// Verifica se inseriu

        Long id = p.getId(); // Pega o Id do pet se inseriu
        assertNotNull(id); // Verifica se o ID nao é null

        // Buscar o objeto
        p = service.getPetById(id); // Pega o Pet Inserido
        assertNotNull(p); // Verifica se encontrou o Pet pelo id informado

        assertEquals("Trovao",p.getNome()); // Compara o Nome do pet informado e registrado no banco de dados
        assertEquals("cao",p.getTipo()); // Compara o tipo do pet informado e registrado no banco de dados

        // Deletar o objeto
        service.delete(id); // Deleta o pet do banco.

        // Verificar se deletou
       try{
           assertNull(service.getPetById(id));  // Verifica se deletou o Pet do banco.
           fail("O carro não foi encontrado");
       }catch(ObjectNotFoundException e){
            //OK
       }


    }
    @Test
    public void testLista() {
        List<PetDTO> pets = service.getPets();
        assertEquals(6, pets.size() );
    }

    @Test
    public void testListaPorTipo() {
        assertEquals(3, service.getPetsByTipo("gato").size() );
        assertEquals(3, service.getPetsByTipo("cao").size() );

        assertEquals(0, service.getPetsByTipo("x").size() );

    }

    @Test
    public void testGet() {
        PetDTO op = service.getPetById(2L);
        assertNotNull(op);
        assertEquals("Bigo", op.getNome());
    }

}
