package com.barbosa.desafio_backend_nubank_JAVA.exceptionshandler;

import com.barbosa.desafio_backend_nubank_JAVA.utils.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionModel> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors()
                .forEach(fieldError -> {
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                });

        ExceptionModel badRequest = ExceptionModel.builder()
                .title("BAD REQUEST")
                .code(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionModel> GenericExceptionHandler(MethodArgumentNotValidException ex) {
        ExceptionModel error = ExceptionModel.builder()
                .title("BAD REQUEST")
                .code(HttpStatus.BAD_REQUEST.value())
                .description(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
