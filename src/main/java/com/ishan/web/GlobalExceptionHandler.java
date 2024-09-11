package com.ishan.web;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public String handleExceptionInMethodArguments(MethodArgumentNotValidException exception){
        return "There's some problems with the method arguments:\n\n" + exception.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler( value = {ConstraintViolationException.class})
    public String handleConstraintViolation(ConstraintViolationException exception){
        return "There's some problem with the violation of constraints: \n\n" + exception.getMessage();
    }
}
