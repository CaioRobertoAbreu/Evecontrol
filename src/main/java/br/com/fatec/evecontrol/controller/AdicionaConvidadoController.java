package br.com.fatec.evecontrol.controller;

import br.com.fatec.evecontrol.controller.data.request.convidado.AdicionaConvidadoRequest;
import br.com.fatec.evecontrol.repository.EventoRepository;
import br.com.fatec.evecontrol.validations.EventoValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/evecontrol/v1")
public class AdicionaConvidadoController {

    private final EventoRepository repository;
    private final EventoValidation validation;

    @PostMapping("/evento/{idEvento}/convidado")
    public ResponseEntity<?> adicionaConvidado(@Valid @PathVariable Long idEvento, @RequestBody AdicionaConvidadoRequest request){

        var evento = repository.findById(idEvento);
        validation.existsEvento(evento);

        var convidado = request.toModel();
        evento.get().getConvidados().add(convidado);

        repository.save(evento.get());

        return ResponseEntity.ok().build();
    }

}

//TODO falta add possibilidade de editar o funcionario e o convidado