package com.example.QuizzApp.exception;

public class ResourceNotFound extends RuntimeException{

    public ResourceNotFound(){
        super();
    }

    public ResourceNotFound(String msg){
        super(msg);
    }
}
