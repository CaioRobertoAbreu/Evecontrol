package br.com.fatec.evecontrol.controller.data.request.evento;

import br.com.fatec.evecontrol.model.DonoEvento;
import br.com.fatec.evecontrol.model.Evento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class EditaEventoRequest {

    @JsonProperty(value = "nome_evento")
    @NotBlank(message = "O campo 'nome' não pode ser vazio")
    private String nomeEvento;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    @JsonProperty(value = "data_evento")
    @NotNull(message = "O campo 'data_evento' não pode ser nulo")
    @Future
    private LocalDateTime dataEvento;

    @JsonProperty(value = "endereco")
    @NotBlank(message = "O campo 'endereco' não pode ser vazio")
    private String endereco;

    @JsonProperty(value = "cep")
    @NotBlank(message = "O campo 'cep' não pode ser vazio")
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
