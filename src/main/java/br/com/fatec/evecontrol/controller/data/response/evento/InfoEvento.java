package br.com.fatec.evecontrol.controller.data.response.evento;

import br.com.fatec.evecontrol.model.Evento;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class InfoEvento {

    @JsonProperty(value = "id_evento")
    private Long idEvento;

    @JsonProperty(value = "nome_evento")
    private String nome;

    @JsonProperty(value = "dono_evento")
    private String donoEvento;

    @JsonProperty(value = "endereco_evento")
    private String endereco;

    @JsonProperty(value = "cep_evento")
    private String cep;

    @JsonProperty(value = "lista_convidados")
    private List<ListaConvidadosResponse> convidados;

    public InfoEvento(Evento evento) {

        this.idEvento = evento.getId();
        this.nome = evento.getNome();
        this.donoEvento = evento.getDonoEvento().getNome();
        this.endereco = evento.getEndereco();
        this.cep = evento.getCep();
        this.convidados = evento.getConvidados()
                                .stream()
                                .map(ListaConvidadosResponse::new)
                                .collect(Collectors.toList());
    }
}
