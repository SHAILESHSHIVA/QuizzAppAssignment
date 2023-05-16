package com.example.QuizzApp.exception;


import com.example.QuizzApp.payLoad.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalException {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Response> ResourceNotFoundException(ResourceNotFound exp){

        String msg =  exp.getMessage();
        boolean status = false;
        Response res = new Response(msg,status);

        return new  ResponseEntity<Response>(res,HttpStatus.NOT_FOUND);


    }






}
