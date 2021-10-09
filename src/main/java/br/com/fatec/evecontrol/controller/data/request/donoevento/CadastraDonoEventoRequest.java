package br.com.fatec.evecontrol.controller.data.request.donoevento;

import br.com.fatec.evecontrol.model.DonoEvento;
import br.com.fatec.evecontrol.validations.CPFUnico;
import br.com.fatec.evecontrol.validations.EmailUnico;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.*;
import java.time.LocalDate;


public class CadastraDonoEventoRequest {

    @NotBlank(message = "O campo 'nome' não pode ser vazio")
    @Size(min = 2, message = "O campo 'nome' deve ter no minimo 2 caracteres")
    @JsonProperty(value = "nome")
    private String nome;

    @NotNull(message = "O campo 'data_nascimento' não pode ser nulo")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty(value = "data_nascimento")
    private LocalDate dataNascimento;

    @NotBlank(message = "O campo 'cpf' não pode ser vazio")
    @CPF(message = "O valor informado para o campo 'CPF' e invalido")
    @JsonProperty(value = "cpf")
    @CPFUnico
    private String cpf;

    @NotBlank(message = "O campo 'email' não pode ser vazio")
    @Email(message = "O valor informado para o campo 'email' e invalido")
    @JsonProperty(value = "email")
    @EmailUnico
    private String email;

    @NotBlank(message = "O campo 'senha' não pode ser vazio")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$",
            message = "O campo 'nova_senha' precisa corresponder ao padrão" +
                    "\n\rDeve conter 1 letra maiúscula" +
                    "\n\rDeve conter 1 letra minúscula" +
                    "\n\rDeve conter 1 número" +
                    "\n\rDeve conter 1 caractere especial" +
                    "\n\rTer pelo menos 8 caracteres")
    @JsonProperty(value = "senha")
    private String senha;

    public DonoEvento toModel(){

        return DonoEvento.builder()
                .nome(this.nome)
                .dataNascimento(this.dataNascimento)
                .cpf(this.cpf)
                .email(this.email)
                .senha(this.senha)
                .build();

        //TODO encriptar senha
    }
}
