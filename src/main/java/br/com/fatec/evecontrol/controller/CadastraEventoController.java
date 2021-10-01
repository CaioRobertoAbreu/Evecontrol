package br.com.fatec.evecontrol.controller;

import br.com.fatec.evecontrol.controller.data.request.evento.CadastraEventoRequest;
import br.com.fatec.evecontrol.controller.data.request.evento.EditaEventoRequest;
import br.com.fatec.evecontrol.controller.data.response.evento.CadastraEventoResponse;
import br.com.fatec.evecontrol.repository.DonoEventoRepository;
import br.com.fatec.evecontrol.repository.EventoRepository;
import br.com.fatec.evecontrol.validations.EventoValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/evecontrol/v1")
public class CadastraEventoController {

    private final DonoEventoRepository donoEventoRepository;
    private final EventoRepository eventoRepository;
    private final EventoValidation validation;

    @PostMapping("/donoevento/{idDonoEvento}/evento/cadastra")
    public ResponseEntity<?> donoEventoCadastraEvento(@Valid @PathVariable Long idDonoEvento, @RequestBody CadastraEventoRequest request) {

        var donoEvento = donoEventoRepository.findById(idDonoEvento);
        validation.existsDonoEvento(donoEvento);

        var evento = request.toModel(donoEvento.get());

        var eventoResponse = new CadastraEventoResponse(eventoRepository.save(evento));

        return ResponseEntity.ok(eventoResponse);
    }

    @PutMapping("/evento/{idEvento}/editar")
    public ResponseEntity<?> donoEventoEditaEvento(@Valid @PathVariable Long idEvento, @RequestBody EditaEventoRequest request) {

        var evento = eventoRepository.findById(idEvento);
        validation.existsEvento(evento);

        eventoRepository.save(request.toModel(evento.get(), evento.get().getDonoEvento()));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/evento/{idEvento}/excluir")
    public ResponseEntity<?> donoEventoRemoveEvento(@Valid @PathVariable Long idEvento) {

        var evento = eventoRepository.findById(idEvento);
        validation.existsEvento(evento);

        eventoRepository.delete(evento.get());

        return ResponseEntity.noContent().build();
    }

}
