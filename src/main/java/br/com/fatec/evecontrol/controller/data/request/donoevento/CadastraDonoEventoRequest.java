package br.com.fatec.evecontrol.controller.data.request.donoevento;

import br.com.fatec.evecontrol.model.DonoEvento;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class CadastraDonoEventoRequest {

    @NotBlank(message = "donoevento.nome.notblank")
    @Size(min = 2, message = "donoevento.nome.size")
    @JsonProperty(value = "nome")
    private String nome;

    @NotNull(message = "donoevento.datanascimento.notnull")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty(value = "data_nascimento")
    private LocalDate dataNascimento;

    @NotBlank(message = "donoevento.cpf.notblank")
    @CPF(message = "donoevento.cpf")
    @JsonProperty(value = "cpf")
    private String cpf;
    //TODO campo unico, nao pode ter dois cadastrados com mesmo cpf

    @NotBlank(message = "donoevento.rg.notblank")
    @JsonProperty(value = "rg")
    //TODO criar validacao customizada para RG
    private String rg;

    @NotBlank(message = "donoevento.email.notblank")
    @Email(message = "donoevento.email")
    @JsonProperty(value = "email")
    private String email;
    //TODO campo unico, nao pode ter dois cadastrados com mesmo email

    @NotBlank(message = "donoevento.senha.notblank")
    @Size(min = 8, max = 100, message = "donoevento.senha.size")
    @JsonProperty(value = "senha")
    private String senha;

    public DonoEvento toModel(){

        return DonoEvento.builder()
                .nome(this.nome)
                .dataNascimento(this.dataNascimento)
                .cpf(this.cpf)
                .rg(this.rg)
                .senha(this.senha)
                .build();

        //TODO encriptar senha
    }
}
