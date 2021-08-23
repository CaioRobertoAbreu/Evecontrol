package br.com.fatec.evento.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("evento/v1")
public class CadastraEventoController {

    @GetMapping
    public String index(){

        return "Estamos online!";
    }
}
