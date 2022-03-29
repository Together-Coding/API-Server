package com.example.authserver.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    // @Valid exception catch
    @ExceptionHandler({MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentConversionNotSupportedException.class})
    public ResponseEntity<ValidExceptionDTO> validExceptionHandler(BindException ex) {
        log.warn("valid Error: {}", ex.getMessage());
        ex.printStackTrace();
        ValidExceptionDTO dto = ValidExceptionDTO.toDto(ex);
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }
}
