package com.educandoweb.course.resources.excpetions;

import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

/** CLASSE ONDE OCORRE O TRATAMENTO MANUAL PARA OS ERROS **/

/** anotação responsável de interceptar as excecções **/
@ControllerAdvice
public class ResourceExceptionHandler {

    /** anotação responsável de interceptar a excecção do tipo informado cai nesse método que faz o tratamento dela **/
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Recurso não encontrado!";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(standardError);
    }

    /** anotacao para interceptar a excecção do tipo DatabaseException **/
    @ExceptionHandler(DatabaseException.class)
    /** atributos do método possuem a excecção e endereço de requisição dela **/
    public ResponseEntity<StandardError> recursoNaoDeletado(DatabaseException e, HttpServletRequest request){
        String error = "Recurso não deletado, pois não foi encontrado!!!";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(standardError);
    }
}
