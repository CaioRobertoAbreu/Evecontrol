package br.com.fatec.evecontrol.controller;

import br.com.fatec.evecontrol.controller.data.response.evento.BuscaInfoEvento;
import br.com.fatec.evecontrol.repository.EventoRepository;
import br.com.fatec.evecontrol.validations.EventoValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/evecontrol/v1")
public class EventoController {

    private final EventoRepository repository;
    private final EventoValidation validation;

    @GetMapping("/evento/{idEvento}/info")
    public ResponseEntity<?> buscaInformacoesEvento(@PathVariable Long idEvento){

        var evento = repository.findById(idEvento);
        validation.existsEvento(evento);

        return ResponseEntity.ok(new BuscaInfoEvento(evento.get()));

    }
}
