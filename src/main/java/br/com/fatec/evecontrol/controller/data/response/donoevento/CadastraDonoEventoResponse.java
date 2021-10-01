package br.com.fatec.evecontrol.controller.data.response.donoevento;

import br.com.fatec.evecontrol.model.DonoEvento;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CadastraDonoEventoResponse {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "data_nascimento")
    private LocalDate dataNascimento;

    @JsonProperty(value = "cpf")
    private String cpf;

    @JsonProperty(value = "rg")
    private String rg;

    public CadastraDonoEventoResponse(DonoEvento entity) {
        this.id = entity.getId();
        this.nome = entity.getNome();
        this.dataNascimento = entity.getDataNascimento();
        this.cpf = entity.getCpf();
        this.rg = entity.getRg();
    }
}
