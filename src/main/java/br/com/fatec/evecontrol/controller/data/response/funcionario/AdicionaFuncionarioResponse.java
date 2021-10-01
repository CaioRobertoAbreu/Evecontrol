package br.com.fatec.evecontrol.controller.data.response.funcionario;

import br.com.fatec.evecontrol.model.Funcionario;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CPF;

public class AdicionaFuncionarioResponse {


    @JsonProperty("nome_funcioanario")
    private String nome;

    @JsonProperty("rg")
    private String rg;

    @JsonProperty("cpf")
    @CPF(message = "funcionario.cpf")
    private String cpf;

    @JsonProperty("telefone")
    private String telefone;

    @JsonProperty("endereco")
    private String endereco;

    public AdicionaFuncionarioResponse(Funcionario funcionario){

        this.nome = funcionario.getNome();
        this.rg = funcionario.getRg();
        this.cpf = funcionario.getCpf();
        this.telefone = funcionario.getTelefone();
        this.endereco = funcionario.getEndereco();
    }
}
