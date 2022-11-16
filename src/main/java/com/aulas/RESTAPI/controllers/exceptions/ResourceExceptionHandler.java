package com.aulas.RESTAPI.controllers.exceptions;

import com.aulas.RESTAPI.services.excpetions.CategoriaInativaException;
import com.aulas.RESTAPI.services.excpetions.EmailJaCadastradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardError> entidadeNaoEncontrada(EntityNotFoundException e,
                                                               HttpServletRequest req){
       StandardError err = new StandardError();
       err.setTimeStamp(Instant.now());
       err.setStatus(HttpStatus.NOT_FOUND.value());
       err.setError("Recurso não encontrado");
       err.setMessage(e.getMessage());
       err.setPath(req.getRequestURI());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> argumentoInvalidoException(MethodArgumentNotValidException e,
                                                               HttpServletRequest req){
        StandardError err = new StandardError();
        err.setTimeStamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Campo obrigatório");
        err.setMessage(e.getFieldError().getDefaultMessage());
        err.setPath(req.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(CategoriaInativaException.class)
    public ResponseEntity<StandardError> categoriaInativaException(CategoriaInativaException e,
                                                                    HttpServletRequest req){
        StandardError err = new StandardError();
        err.setTimeStamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Recurso inativo");
        err.setMessage(e.getMessage());
        err.setPath(req.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<StandardError> emailJaCadastradoException(EmailJaCadastradoException e,
                                                                   HttpServletRequest req){
        StandardError err = new StandardError();
        err.setTimeStamp(Instant.now());
        err.setStatus(HttpStatus.CONFLICT.value());
        err.setError("Recurso já existente");
        err.setMessage(e.getMessage());
        err.setPath(req.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(err);
    }
}
