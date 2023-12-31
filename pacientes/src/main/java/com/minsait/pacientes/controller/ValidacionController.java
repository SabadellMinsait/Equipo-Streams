package com.minsait.pacientes.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidacionController {
    @ResponseStatus(code=HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public Map<String, String> validaExcepciones(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<String, String>();
        ex.getBindingResult().getAllErrors().forEach((error)-> {
        String linea = ((FieldError) error).getField();
        String mensage = error.getDefaultMessage();
        errors.put(linea, mensage);
        });
        return errors;
     }
}
