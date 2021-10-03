package br.com.fatec.evecontrol.controller.data.request.donoevento;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
public class EditaSenhaDonoEventoRequest {

    @NotBlank(message = "O campo 'senha_antiga' não pode estar vazio")
    @JsonProperty(value = "senha_antiga")
    private String senhaAntiga;

    @NotBlank(message = "O campo 'nova_senha' não pode estar vazio")
    @JsonProperty(value = "nova_senha")
    @Pattern(regexp = "", message = "O campo 'nova_senha' precisa corresponder ao padrão") //TODO add regex
    private String novaSenha;

}
