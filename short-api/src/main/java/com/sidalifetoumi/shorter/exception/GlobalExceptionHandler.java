package com.sidalifetoumi.shorter.exception;

/*
    @Author Fetoumi Sidali
    @Created 11/23/2025
*/

import com.sidalifetoumi.shorter.url.UrlNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {




    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<Map<String,String>> urlNotFoundExceptionHandler(UrlNotFoundException exception){

        Map<String,String> error = Map.of("error",exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String> > methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){
        Map<String ,String> messages = new HashMap<>();
        exception.getBindingResult().
                getFieldErrors().forEach(
                        fieldError -> messages.putIfAbsent(fieldError.getField(),fieldError.getDefaultMessage())
                );
        return ResponseEntity.badRequest().body(messages);
    }
}
