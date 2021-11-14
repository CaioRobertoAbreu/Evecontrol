package br.com.fatec.evecontrol.controller;

import br.com.fatec.evecontrol.controller.data.request.donoevento.CadastraDonoEventoRequest;
import br.com.fatec.evecontrol.controller.data.request.donoevento.EditaDonoEventoRequest;
import br.com.fatec.evecontrol.controller.data.request.donoevento.EditaSenhaDonoEventoRequest;
import br.com.fatec.evecontrol.controller.data.response.donoevento.CadastraDonoEventoResponse;
import br.com.fatec.evecontrol.exception.ExceptionEvecontrolNotFound;
import br.com.fatec.evecontrol.repository.DonoEventoRepository;
import br.com.fatec.evecontrol.validations.EventoValidation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/evecontrol/v1/donoevento")
public class DonoEventoController {

    private final DonoEventoRepository repository;
    private final EventoValidation validation;

    @PostMapping("/cadastra")
    public ResponseEntity<CadastraDonoEventoResponse> cadastraDonoEvento(@Valid @RequestBody CadastraDonoEventoRequest request){

        var response = new CadastraDonoEventoResponse(repository.save(request.toModel(new BCryptPasswordEncoder())));

        return ResponseEntity.ok(response);

    }

    @PutMapping("{idDonoEvento}/editar")
    public ResponseEntity<?> editaDonoEvento(@PathVariable Long idDonoEvento, @Valid @RequestBody EditaDonoEventoRequest request){

        var entity = repository.findById(idDonoEvento);

        validation.existsDonoEvento(entity);
        repository.save(request.toModel(idDonoEvento, entity.get().getCpf(), entity.get().getSenha()));

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{idDonoEvento}/pass")
    public ResponseEntity<?> editaSenhaDonoEvento(@PathVariable Long idDonoEvento, @Valid @RequestBody EditaSenhaDonoEventoRequest request){

        var entity = repository.findById(idDonoEvento);

        validation.existsDonoEvento(entity);

        if(!entity.get().getSenha().equals(request.getSenhaAntiga())){
            throw new ExceptionEvecontrolNotFound(HttpStatus.UNPROCESSABLE_ENTITY.value(), HttpStatus.UNPROCESSABLE_ENTITY.toString(),
                    "Senha antiga inválida");
        }

        entity.get().setSenha(request.getNovaSenha());

        repository.save(entity.get());

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{idDonoEvento}/deletarConta")
    public ResponseEntity<?> deletarConta(@PathVariable Long idDonoEvento){

        var donoEvento = repository.findById(idDonoEvento);
        validation.existsDonoEvento(donoEvento);

        repository.deleteById(donoEvento.get().getId());

        return ResponseEntity.noContent().build();

    }

}
