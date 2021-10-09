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
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
            message = "O campo 'nova_senha' precisa corresponder ao padrão" +
                    "\n\rDeve conter 1 letra maiúscula" +
                    "\n\rDeve conter 1 letra minúscula" +
                    "\n\rDeve conter 1 número" +
                    "\n\rDeve conter 1 caractere especial" +
                    "\n\rTer pelo menos 8 caracteres")
    private String novaSenha;

}
