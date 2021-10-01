package br.com.fatec.evecontrol.controller.data.request.donoevento;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class EditaSenhaDonoEventoRequest {

    @JsonProperty(value = "senha_antiga")
    private String senhaAntiga;

    @JsonProperty(value = "nova_senha")
    private String novaSenha;

}
