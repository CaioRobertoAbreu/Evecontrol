package br.com.fatec.evecontrol.controller.data.response.convidado;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public class InfoConvidadoResponse {

    @JsonProperty("cpf")
    private String cpf;

    @JsonProperty("convidados")
    private List<ConvidadoResponse> convidados;
}
