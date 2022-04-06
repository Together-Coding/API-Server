package com.example.authserver.exception;

import com.example.authserver.exception.custom.AlreadyExistsException;
import com.example.authserver.exception.custom.ForbiddenException;
import com.example.authserver.exception.custom.ServerRuntimeException;
import io.jsonwebtoken.SignatureException;
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

    @ExceptionHandler({SignatureException.class})
    public ResponseEntity<String> forbiddenHandler(SignatureException ex){
        String message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    // return CONFLICT
    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<String> alreadyExistsHandler(AlreadyExistsException ex){
        String message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }

    // return FORBIDDEN
    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<String> forbiddenHandler(ForbiddenException ex){
        String message = ex.getMessage();
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ServerRuntimeException.class})
    public ResponseEntity<String> serverRuntimeHandler(ServerRuntimeException ex){
        String message = ex.getMessage();
        log.error("serverRuntimeException: {}", message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> runtimeHandler(RuntimeException ex) {
        String message = ex.getMessage();
        ex.printStackTrace();
        log.error("RuntimeException: {}", message);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
