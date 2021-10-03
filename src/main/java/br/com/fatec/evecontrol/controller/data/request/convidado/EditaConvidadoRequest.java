package br.com.fatec.evecontrol.controller.data.request.convidado;

import br.com.fatec.evecontrol.model.Convidado;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;

public class EditaConvidadoRequest {

    @JsonProperty(value = "nome_convidado")
    @NotBlank(message = "O campo 'nome' não pode ser vazio")
    private String nome;

    @JsonProperty(value = "rg")
    @NotBlank(message = "O campo 'rg' não pode ser vazio")
    private String rg;

    @JsonProperty(value = "cpf")
    @NotBlank(message = "O campo 'cpf' não pode ser vazio")
    private String cpf;

    @JsonProperty(value = "telefone")
    @NotBlank(message = "O campo 'telefone' não pode ser vazio")
    private String telefone;

    public Convidado toModel(Convidado convidado){

        return Convidado.builder()
                .id(convidado.getId())
                .nome(this.nome)
                .rg(this.rg)
                .cpf(this.cpf)
                .telefone(this.telefone)
                .confirmacaoPresenca(convidado.isConfirmacaoPresenca())
                .build();
    }

}
