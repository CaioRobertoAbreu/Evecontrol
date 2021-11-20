package br.com.fatec.evecontrol.controller.data.request;

import br.com.fatec.evecontrol.model.AvaliacaoEvento;
import br.com.fatec.evecontrol.model.Evento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AvaliacaoEventoRequest {

    @JsonProperty("nota")
    @NotNull
    @Min(0)
    @Max(5)
    private Integer nota;

    @JsonProperty("comentarios")
    @Max(400)
    private String comentario;

    public AvaliacaoEvento toModel(Evento evento){

        return AvaliacaoEvento.builder()
                .evento(evento)
                .nota(this.nota)
                .comentario(this.comentario)
                .build();
    }
}
