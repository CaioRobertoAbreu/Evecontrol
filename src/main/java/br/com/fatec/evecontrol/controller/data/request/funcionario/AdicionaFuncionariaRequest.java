package br.com.fatec.evecontrol.controller.data.request.funcionario;

import br.com.fatec.evecontrol.model.Evento;
import br.com.fatec.evecontrol.model.Funcionario;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

public class AdicionaFuncionariaRequest {

    @JsonProperty("nome_funcioanario")
    @NotBlank(message = "funcionario.nome.notblank")
    private String nome;

    @JsonProperty("rg")
    @NotBlank(message = "funcionario.rg.notblank")
    private String rg;

    @JsonProperty("cpf")
    @CPF(message = "funcionario.cpf")
    @NotBlank(message = "funcionario.cpf.notblank")
    private String cpf;

    @JsonProperty("telefone")
    @NotBlank(message = "funcionario.telefone.notblank")
    private String telefone;

    @JsonProperty("endereco")
    @NotBlank(message = "funcionario.endereco.notblank")
    private String endereco;

    public Funcionario toModel(Evento evento){

        var funcionario =  Funcionario.builder()
                .nome(this.nome)
                .rg(this.rg)
                .cpf(this.cpf)
                .telefone(this.telefone)
                .endereco(this.endereco)
                .eventos(new ArrayList<>())
                .build();

        funcionario.getEventos().add(evento);

        return funcionario;
    }

    //TODO Criar validacao para RG. Posso ser dono de evento e ter meu cpf como funcionario?

}
