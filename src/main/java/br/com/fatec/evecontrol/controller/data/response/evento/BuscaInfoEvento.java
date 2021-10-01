package br.com.fatec.evecontrol.controller.data.response.evento;

import br.com.fatec.evecontrol.model.Evento;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BuscaInfoEvento {

    @JsonProperty(value = "Informacoes_evento")
    private InfoEvento infoEvento;

    public BuscaInfoEvento(Evento evento) {

        this.infoEvento = new InfoEvento(evento);
    }
}
