package barbearia.com.br.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String paginaInicial() {
        // Retorna o nome do arquivo HTML dentro de src/main/resources/templates sem a extensão .html
        return "index";
    }
}