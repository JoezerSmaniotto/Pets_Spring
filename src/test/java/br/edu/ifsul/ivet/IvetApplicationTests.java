package br.edu.ifsul.ivet;

import br.edu.ifsul.ivet.domain.Pet;
import br.edu.ifsul.ivet.domain.PetService;
import br.edu.ifsul.ivet.domain.dto.PetDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.runner.RunWith;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertFalse;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
class IvetApplicationTests {

    @Autowired
    private PetService service;
    @Test
    public void test1() {
        Pet pet = new Pet();
        pet.setNome("Trovao");
        pet.setTipo("cao");

        PetDTO p = service.insert(pet);// Insere um pet

        assertNotNull(p);// Verifica se inseriu

        Long id = p.getId(); // Pega o Id do pet se inseriu
        assertNotNull(id); // Verifica se o ID nao é null

        // Buscar o objeto
        Optional<PetDTO> op = service.getPetById(id); // Pega o Pet Inserido
        assertTrue(op.isPresent()); // Verifica se encontrou o Pet pelo id informado

        p = op.get(); // Pega o Pet
        assertEquals("Trovao",p.getNome()); // Compara o Nome do pet informado e registrado no banco de dados
        assertEquals("cao",p.getTipo()); // Compara o tipo do pet informado e registrado no banco de dados

        // Deletar o objeto
        service.delete(id); // Deleta o pet do banco.

        // Verificar se deletou
        assertFalse(service.getPetById(id).isPresent());  // Verifica se deletou o Pet do banco.

    }

}
