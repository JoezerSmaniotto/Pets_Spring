package br.edu.ifsul.ivet.api;

import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // converter essa classe em um web service.
@RequestMapping("/") // O RequestMapping mapeia o  web service, neste caso para o  "/" raiz do projeto.
public class IndexController {

    @GetMapping()
    public ResponseEntity<String> getIndex() {
        return ResponseEntity.ok("Consulte a documentação da API.");
    }



    //UserDetails representa o usuário logado na sessão
    /*@GetMapping("/user/userInfo")
    public UserDetails userInfo(@AuthenticationPrincipal UserDetails user) {
        return user;
    }*/

}
