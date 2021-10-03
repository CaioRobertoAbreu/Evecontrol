package br.com.fatec.evecontrol.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorValidation {

    @JsonProperty(value = "campo")
    private String campo;

    @JsonProperty(value = "mensagem")
    private String mensagem;

    public ErrorValidation(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }
}
