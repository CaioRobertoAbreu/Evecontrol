package br.com.fatec.evecontrol.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionEvecontrolNotFound extends RuntimeException {

    private int status;
    private String messageStatus;
    private String message;

}
