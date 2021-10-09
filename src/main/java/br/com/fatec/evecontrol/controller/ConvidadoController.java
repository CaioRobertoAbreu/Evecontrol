package br.com.fatec.evecontrol.controller;

import br.com.fatec.evecontrol.controller.data.request.convidado.AdicionaConvidadoRequest;
import br.com.fatec.evecontrol.controller.data.request.convidado.EditaConvidadoRequest;
import br.com.fatec.evecontrol.controller.data.response.convidado.ConvidadoResponse;
import br.com.fatec.evecontrol.controller.data.response.convidado.InfoConvidadoResponse;
import br.com.fatec.evecontrol.exception.ExceptionEvecontrolNotFound;
import br.com.fatec.evecontrol.repository.ConvidadoRepository;
import br.com.fatec.evecontrol.repository.EventoRepository;
import br.com.fatec.evecontrol.validations.EventoValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/evecontrol/v1")
public class ConvidadoController {

    private final EventoRepository eventoRepository;
    private final ConvidadoRepository convidadoRepository;
    private final EventoValidation validation;


    @GetMapping("/evento/{idEvento}/convidado/{cpfConvidado}/infos")
    public ResponseEntity<?> buscaInfoConvidado(@PathVariable Long idEvento, @PathVariable String cpfConvidado){

        var evento = eventoRepository.findById(idEvento);
        validation.existsEvento(evento);

        var convidados = convidadoRepository.findAllByCpf(cpfConvidado);

        if(convidados.isEmpty()){
            throw new ExceptionEvecontrolNotFound(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.toString(),
                    "Nenhum convidado encontrado para este evento");
        }

        var infoConvidadoResponse = InfoConvidadoResponse.builder()
                                                                            .cpf(cpfConvidado)
                                                                            .convidados(convidados.stream()
                                                                                                  .map(c -> new ConvidadoResponse(c.getId(), c.getNome()))
                                                                                                  .collect(Collectors.toList()))
                                                                            .build();

        return ResponseEntity.ok(infoConvidadoResponse);
    }

    @PostMapping("/evento/{idEvento}/convidado")
    public ResponseEntity<?> adicionaConvidado(@PathVariable Long idEvento, @Valid @RequestBody AdicionaConvidadoRequest request){

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
        validation.existsConvidado(entityConvidado);

        convidadoRepository.save(request.toModel(entityConvidado.get()));

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("evento/{idEvento}/convidado/{idConvidado}/remover")
    @Transactional
    public ResponseEntity<?> removeConvidado(@PathVariable Long idEvento, @PathVariable Long idConvidado){

        var entityEvento = eventoRepository.findById(idEvento);
        var entityConvidado = convidadoRepository.findById(idConvidado);

        validation.existsEvento(entityEvento);
        validation.existsConvidado(entityConvidado);

        entityEvento.get().getConvidados().remove(entityConvidado.get());
        eventoRepository.save(entityEvento.get());
        convidadoRepository.deleteById(entityConvidado.get().getId());

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/evento/{idEvento}/convidado/{idConvidado}/confirmarPresenca")
    @Transactional
    public ResponseEntity<?> confirmaConvidado(@PathVariable Long idEvento, @PathVariable Long idConvidado){

        var evento = eventoRepository.findById(idEvento);
        validation.existsEvento(evento);

        var convidado = convidadoRepository.findById(idConvidado);
        validation.existsConvidado(convidado);

        validation.verificaConvidadoConfirmado(convidado);
        convidado.get().confirmarPresenca();

        convidadoRepository.save(convidado.get());

        return ResponseEntity.ok("Presença confirmada");
    }

}
