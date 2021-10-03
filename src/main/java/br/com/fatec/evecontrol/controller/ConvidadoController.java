package br.com.fatec.evecontrol.controller;

import br.com.fatec.evecontrol.controller.data.request.convidado.AdicionaConvidadoRequest;
import br.com.fatec.evecontrol.controller.data.request.convidado.EditaConvidadoRequest;
import br.com.fatec.evecontrol.repository.ConvidadoRepository;
import br.com.fatec.evecontrol.repository.EventoRepository;
import br.com.fatec.evecontrol.validations.EventoValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/evecontrol/v1")
public class ConvidadoController {

    private final EventoRepository eventoRepository;
    private final ConvidadoRepository convidadoRepository;
    private final EventoValidation validation;

    @PostMapping("/evento/{idEvento}/convidado")
    public ResponseEntity<?> adicionaConvidado(@Valid @PathVariable Long idEvento, @RequestBody AdicionaConvidadoRequest request){

        var evento = eventoRepository.findById(idEvento);
        validation.existsEvento(evento);

        var convidado = request.toModel();
        evento.get().getConvidados().add(convidado);

        eventoRepository.save(evento.get());

        return ResponseEntity.ok().build();
    }

    @PutMapping("evento/{idEvento}/convidado/{idConvidado}/editar")
    public ResponseEntity<?> editaConvidado(@PathVariable Long idEvento, @PathVariable Long idConvidado,
                                             @Valid @RequestBody EditaConvidadoRequest request){

        var entityEvento = eventoRepository.findById(idEvento);
        var entityConvidado = convidadoRepository.findById(idConvidado);

        validation.existsEvento(entityEvento);
        validation.existisConvidado(entityConvidado);

        convidadoRepository.save(request.toModel(entityConvidado.get()));

        return ResponseEntity.noContent().build();
    }

}
