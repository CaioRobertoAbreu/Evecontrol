package br.com.fatec.evecontrol.controller.data.request;

import br.com.fatec.evecontrol.model.DonoEvento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class EditaDonoEventoRequest {

    @NotBlank(message = "donoevento.nome.notblank")
    @Size(min = 2, message = "donoevento.nome.size")
    @JsonProperty(value = "nome")
    private String nome;

    @NotNull(message = "donoevento.datanascimento.notnull")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty(value = "data_nascimento")
    private LocalDate dataNascimento;

    @NotBlank(message = "donoevento.rg.notblank")
    @JsonProperty(value = "rg")
    //TODO criar validacao customizada para RG
    private String rg;

    @NotBlank(message = "donoevento.email.notblank")
    @Email(message = "donoevento.email")
    @JsonProperty(value = "email")
    private String email;

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
