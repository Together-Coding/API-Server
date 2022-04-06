package com.example.authserver.exception.custom;

public class InvalidArgsException extends ServerRuntimeException{
    public InvalidArgsException(String msg) {
        super(msg);
    }
}
