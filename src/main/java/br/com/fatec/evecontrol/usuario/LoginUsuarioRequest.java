package br.com.fatec.evecontrol.usuario;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class LoginUsuarioRequest {

    @JsonProperty("email")
    private String email;

    @JsonProperty("senha")
    private String senha;


}
