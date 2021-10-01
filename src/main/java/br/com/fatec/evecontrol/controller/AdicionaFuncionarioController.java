package br.com.fatec.evecontrol.controller;

import br.com.fatec.evecontrol.controller.data.request.funcionario.AdicionaFuncionariaRequest;
import br.com.fatec.evecontrol.controller.data.response.funcionario.AdicionaFuncionarioResponse;
import br.com.fatec.evecontrol.repository.EventoRepository;
import br.com.fatec.evecontrol.validations.EventoValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/evecontrol/v1")
public class AdicionaFuncionarioController {

    private final EventoRepository repository;
    private final EventoValidation validation;

    @PostMapping("/evento/{idEvento}/funcionario")
    @Transactional
    public ResponseEntity<AdicionaFuncionarioResponse> adicionaFuncionario(@Valid  @PathVariable Long idEvento,
                                                      @RequestBody AdicionaFuncionariaRequest request){

        var evento = repository.findById(idEvento);
        validation.existsEvento(evento);

        var funcionario = request.toModel(evento.get());
        evento.get().getFuncionarios().add(funcionario);

        repository.save(evento.get());

        return ResponseEntity.ok(new AdicionaFuncionarioResponse(funcionario));

    }

}
