package br.com.fatec.evecontrol.controller.data.request.evento;

import br.com.fatec.evecontrol.model.DonoEvento;
import br.com.fatec.evecontrol.model.Evento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
public class EditaEventoRequest {

    @JsonProperty(value = "nome_evento")
    @NotBlank(message = "evento.nome.notblank")
    private String nomeEvento;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonProperty(value = "data_evento")
    @NotNull(message = "evento.dataevento.notnull")
    private LocalDateTime dataEvento;

    @JsonProperty(value = "endereco")
    @NotBlank(message = "evento.endereco.notblank")
    private String endereco;

    @JsonProperty(value = "cep")
    @NotBlank(message = "evento.cep.notblank")
    private String cep;

    public Evento toModel(Evento evento, DonoEvento dono) {

        return Evento.builder()
                .id(evento.getId())
                .nome(this.nomeEvento)
                .data(this.dataEvento)
                .endereco(this.endereco)
                .cep(this.cep)
                .donoEvento(dono)
                .convidados(evento.getConvidados())
                .build();
    }
}
