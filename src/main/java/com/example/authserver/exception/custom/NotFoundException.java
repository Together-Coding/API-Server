package com.example.authserver.exception.custom;

public class NotFoundException extends ServerRuntimeException{
    public NotFoundException(String msg){
        super(msg);
    }
}
