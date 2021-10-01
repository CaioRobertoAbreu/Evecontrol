package br.com.fatec.evecontrol.controller.data.response.evento;

import br.com.fatec.evecontrol.model.Evento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class CadastraEventoResponse {

    @JsonProperty(value = "nome_evento")
    private String nomeEvento;

    @JsonProperty(value = "data_evento")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private LocalDateTime dataEvento;

    @JsonProperty(value = "endereco")
    private String endereco;

    @JsonProperty(value = "cep")
    private String cep;

    public CadastraEventoResponse(Evento entity) {
        this.nomeEvento = entity.getNome();
        this.dataEvento = entity.getData();
        this.endereco = entity.getEndereco();
        this.cep = entity.getCep();
    }

}
