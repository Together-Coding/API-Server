package com.example.authserver.exception.custom;

public class AlreadyExistsException extends ServerRuntimeException{
    public AlreadyExistsException(String msg) {
        super(msg);
    }
}
