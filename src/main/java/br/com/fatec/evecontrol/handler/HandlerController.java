package br.com.fatec.evecontrol.handler;

import br.com.fatec.evecontrol.exception.ExceptionEvecontrol;
import br.com.fatec.evecontrol.exception.ExceptionEvecontrolNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class HandlerController {

    @ExceptionHandler(ExceptionEvecontrolNotFound.class)
    public ResponseEntity<ExceptionEvecontrol> exceptionEvecontrolNotFound(ExceptionEvecontrolNotFound exception){

        var response = new ExceptionEvecontrol(LocalDateTime.now(), exception.getStatus(), exception.getMessageStatus(),
                exception.getMessage());

        return ResponseEntity.status(response.getStatus()).body(response);


    }
}
