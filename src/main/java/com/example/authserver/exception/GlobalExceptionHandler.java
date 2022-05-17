package com.example.authserver.exception;

import com.example.authserver.exception.custom.AlreadyExistsException;
import com.example.authserver.exception.custom.ForbiddenException;
import com.example.authserver.exception.custom.ServerRuntimeException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    @Getter
    @AllArgsConstructor
    static class ErrorResult<T> {
        private T message;

        public static <T> ErrorResult from(T exceptionMessage) {
            return new ErrorResult<>(exceptionMessage);
        }
    }

    private <T> ResponseEntity<ErrorResult<T>> createErrorResponse(T exception, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(ErrorResult.from(exception), headers, status);
    }


    // @Valid exception catch
    @ExceptionHandler({MethodArgumentNotValidException.class,
            MethodArgumentTypeMismatchException.class,
            MethodArgumentConversionNotSupportedException.class})
    public ResponseEntity<?> validExceptionHandler(BindException ex) {
        log.warn("valid Error: {}", ex.getMessage());
        ex.printStackTrace();
        ValidExceptionDTO dto = ValidExceptionDTO.toDto(ex);
        return createErrorResponse(dto, HttpStatus.UNPROCESSABLE_ENTITY);
    }


    // return CONFLICT
    @ExceptionHandler({AlreadyExistsException.class})
    public ResponseEntity<?> alreadyExistsHandler(AlreadyExistsException ex){
        log.warn("invalid args Error: {}", ex.getMessage());
        String message = ex.getMessage();
        return createErrorResponse(message, HttpStatus.CONFLICT);
    }

    // return FORBIDDEN
    @ExceptionHandler({ForbiddenException.class})
    public ResponseEntity<?> forbiddenHandler(ForbiddenException ex){
        log.warn("forbidden Error: {}", ex.getMessage());
        String message = ex.getMessage();
        return createErrorResponse(message, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ServerRuntimeException.class})
    public ResponseEntity<?> serverRuntimeHandler(ServerRuntimeException ex){
        String message = ex.getMessage();
        log.error("serverRuntimeException: {}", message);
        return createErrorResponse(message, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<?> constraintViolationHandler(ConstraintViolationException ex){
        log.warn("ConstraintViolation Error: {}", ex.getMessage());
        String message = ex.getMessage();
        return createErrorResponse(message, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({
            IllegalArgumentException.class, IllegalStateException.class,
            TypeMismatchException.class, HttpMessageNotReadableException.class,
            MissingServletRequestParameterException.class})
    public ResponseEntity<?> badRequestExceptionHandler(Exception ex) {
        log.warn("Bad request exception occurred: {}", ex.getMessage());
        String message = ex.getMessage();
        return createErrorResponse(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex){
        log.warn("HttpRequestMethodNotSupportedException Error: {}", ex.getMessage());
        String message = ex.getMessage();
        return createErrorResponse(message, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public ResponseEntity<?> runtimeHandler(RuntimeException ex) {
        String message = ex.getMessage();
        ex.printStackTrace();
        log.error("RuntimeException: {}", message);
        String clientMessage = "알 수 없는 에러가 발생했습니다. 서버 관리자에게 문의하세요.";
        return createErrorResponse(clientMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({JwtException.class})
    public ResponseEntity<String> JwtHandler(JwtException ex) {
        String message = ex.getMessage();
        ex.printStackTrace();
        log.error("JwtException: {}", message);
        return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
    }
}
