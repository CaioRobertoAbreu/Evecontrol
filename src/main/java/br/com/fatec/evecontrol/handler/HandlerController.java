package br.com.fatec.evecontrol.handler;

import br.com.fatec.evecontrol.exception.ErrorValidation;
import br.com.fatec.evecontrol.exception.ExceptionEvecontrol;
import br.com.fatec.evecontrol.exception.ExceptionEvecontrolNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerController {

    private static final String MENSAGEM_ERRO = "Foram encontrados erros ao processar requisição";
    @ExceptionHandler(ExceptionEvecontrolNotFound.class)
    public ResponseEntity<ExceptionEvecontrol> exceptionEvecontrolNotFound(ExceptionEvecontrolNotFound exception){

        var response = new ExceptionEvecontrol(LocalDateTime.now(), exception.getStatus(), exception.getMessageStatus(),
                exception.getMessage(), null);

        return ResponseEntity.status(response.getStatus()).body(response);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionEvecontrol> exceptionEvencontronMethodArgumentNotValidException(
                                                                            MethodArgumentNotValidException exception){

        var erros = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ErrorValidation(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        var response = new ExceptionEvecontrol(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.toString(),
                MENSAGEM_ERRO, erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);


    }
}
