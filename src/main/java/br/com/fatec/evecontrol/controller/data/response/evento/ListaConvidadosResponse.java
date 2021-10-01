package br.com.fatec.evecontrol.controller.data.response.evento;

import br.com.fatec.evecontrol.model.Convidado;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ListaConvidadosResponse {

    @JsonProperty(value = "nome_convidado")
    private String nome;

    @JsonProperty(value = "rg_convidado")
    private String rg;

    @JsonProperty(value = "cpf_convidado")
    private String cpf;

    @JsonProperty(value = "telefone_convidado")
    private String telefone;

    @JsonProperty(value = "confirmacao_presenca_convidado")
    private boolean confirmacaoPresenca = false;

    public ListaConvidadosResponse(Convidado convidado) {

        this.nome = convidado.getNome();
        this.rg = convidado.getRg();
        this.cpf = convidado.getCpf();
        this.telefone = convidado.getTelefone();
        this.confirmacaoPresenca = convidado.isConfirmacaoPresenca();
    }
}
