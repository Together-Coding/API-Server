package com.example.authserver.exception.custom;

public abstract class ServerRuntimeException extends RuntimeException{
    public ServerRuntimeException(String msg){
        super(msg);
    }
}
