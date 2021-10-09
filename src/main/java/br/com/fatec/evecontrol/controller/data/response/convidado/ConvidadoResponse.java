package br.com.fatec.evecontrol.controller.data.response.convidado;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConvidadoResponse {

    @JsonProperty("id_convidado")
    private Long id;

    @JsonProperty("nome_convidado")
    private String nome;

}
