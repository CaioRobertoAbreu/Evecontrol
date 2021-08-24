package br.com.fatec.evecontrol.controller;

import br.com.fatec.evecontrol.controller.data.request.CadastraDonoEventoRequest;
import br.com.fatec.evecontrol.controller.data.request.EditaDonoEventoRequest;
import br.com.fatec.evecontrol.controller.data.request.EditaSenhaDonoEventoRequest;
import br.com.fatec.evecontrol.controller.data.response.CadastraDonoEventoResponse;
import br.com.fatec.evecontrol.model.DonoEvento;
import br.com.fatec.evecontrol.repository.DonoEventoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/evecontrol/v1/donoevento")
public class CadastraDonoEventoController {

    private final DonoEventoRepository repository;

    @PostMapping("/cadastra")
    public ResponseEntity<CadastraDonoEventoResponse> cadastraDonoEvento(@Valid @RequestBody CadastraDonoEventoRequest request){

        var response = new CadastraDonoEventoResponse(repository.save(request.toModel()));

        return ResponseEntity.ok(response);

    }

    @PutMapping("{idDonoEvento}/editar")
    public ResponseEntity<?> editaDonoEvento(@PathVariable Long idDonoEvento, @Valid @RequestBody EditaDonoEventoRequest request){

        var entity = repository.findById(idDonoEvento);

        existsDonoEvento(entity);
        repository.save(request.toModel(idDonoEvento, entity.get().getCpf(), entity.get().getSenha()));

        return ResponseEntity.noContent().build();
    }

    @PutMapping("{idDonoEvento}/pass")
    public ResponseEntity<?> editaSenhaDonoEvento(@PathVariable Long idDonoEvento, @Valid @RequestBody EditaSenhaDonoEventoRequest request){

        var entity = repository.findById(idDonoEvento);

        existsDonoEvento(entity);

        entity.get().setSenha(request.getNovaSenha());

        repository.save(entity.get());

        return ResponseEntity.noContent().build();
    }

    private void existsDonoEvento(Optional<DonoEvento> optional){

        if(optional.isEmpty()){
            throw new RuntimeException();
            //TODO criar excecao para ser lancada
        }

    }

    //TODO testar no postman demais endpoints
}
