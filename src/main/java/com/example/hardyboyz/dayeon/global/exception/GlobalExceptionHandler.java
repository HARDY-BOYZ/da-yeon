package com.example.hardyboyz.dayeon.global.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e,
                                                        HttpServletRequest request) {
        log.error("[CustomException] {} is occurred. uri : {}",
                e.getStatusCode(), request.getRequestURI());
        Map<String, Object> body = Map.of(
                "timestamp", new Date(),
                "status", e.getStatusCode(),
                "errorMessage", e.getMessage()
        );
        return new ResponseEntity<>(body, HttpStatus.valueOf(e.getStatusCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e, HttpServletRequest request) {
        log.error("[MethodArgumentNotValidException] is occurred. uri : {}",
                request.getRequestURI());
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors()
                .forEach(c -> errors.put(((FieldError) c).getField(), c.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<String> handleException(Exception e,
                                                  HttpServletRequest request) {
        log.error("[Exception] {} is occurred. uri: {}",
                e.getMessage(), request.getRequestURI());
        return ResponseEntity.internalServerError().body("알 수 없는 에러가 발생하였습니다.");
    }

}
