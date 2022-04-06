package com.example.authserver.exception.custom;

public class ForbiddenException extends ServerRuntimeException{
    public ForbiddenException(String msg){
        super(msg);
    }
}
