package br.com.fatec.evecontrol.controller.data.request.donoevento;

import br.com.fatec.evecontrol.model.DonoEvento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class EditaDonoEventoRequest {

    @NotBlank(message = "O campo 'nome' não pode ser vazio")
    @Size(min = 2, message = "O campo 'nome' deve ter no minimo 2 caracteres")
    @JsonProperty(value = "nome")
    private String nome;

    @NotNull(message = "O campo 'data_nascimento' não pode ser nulo")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty(value = "data_nascimento")
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo 'rg' não pode ser vazio")
    @JsonProperty(value = "rg")
    //TODO criar validacao customizada para RG
    private String rg;

    public DonoEvento toModel(Long idDonoEvento, String cpf, String senha) {

        return DonoEvento.builder()
                .id(idDonoEvento)
                .nome(this.nome)
                .dataNascimento(this.dataNascimento)
                .cpf(cpf)
                .rg(this.rg)
                .senha(senha)
                .build();
    }

    //TODO criar editor para senha
}
