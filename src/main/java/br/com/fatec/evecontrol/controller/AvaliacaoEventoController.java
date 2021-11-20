package br.com.fatec.evecontrol.controller;

import br.com.fatec.evecontrol.controller.data.request.AvaliacaoEventoRequest;
import br.com.fatec.evecontrol.repository.AvaliacaoEventoRepository;
import br.com.fatec.evecontrol.repository.EventoRepository;
import br.com.fatec.evecontrol.validations.EventoValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evecontrol/v1")
@AllArgsConstructor
public class AvaliacaoEventoController {

    private final EventoRepository eventoRepository;
    private final AvaliacaoEventoRepository avaliacaoEventoRepository;
    private final EventoValidation validation;

    @PostMapping("/evento/{idEvento}/avaliacao")
    public ResponseEntity<?> avaliaEvento(@PathVariable Long idEvento,
                                          @RequestBody AvaliacaoEventoRequest request) {

        var evento = eventoRepository.findById(idEvento);
        validation.existsEvento(evento);

        avaliacaoEventoRepository.save(request.toModel(evento.get()));

        return ResponseEntity.ok().build();
    }
}
