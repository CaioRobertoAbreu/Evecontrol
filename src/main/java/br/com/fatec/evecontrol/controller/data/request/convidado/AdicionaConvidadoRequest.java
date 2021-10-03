package br.com.fatec.evecontrol.controller.data.request.convidado;

import br.com.fatec.evecontrol.model.Convidado;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class AdicionaConvidadoRequest {

    @JsonProperty(value = "nome_convidado")
    @NotBlank(message = "O campo 'nome' não pode ser vazio")
    private String nome;

    @JsonProperty(value = "cpf")
    @NotBlank(message = "O campo 'cpf' não pode ser vazio")
    private String cpf;

    @JsonProperty(value = "telefone")
    @NotBlank(message = "O campo 'telefone' não pode ser vazio")
    private String telefone;

    public Convidado toModel(){

        return Convidado.builder()
                .nome(this.nome)
                .cpf(this.cpf)
                .telefone(this.telefone)
                .build();
    }

    //TODO add pattern (ou validacao) para telefone

}
