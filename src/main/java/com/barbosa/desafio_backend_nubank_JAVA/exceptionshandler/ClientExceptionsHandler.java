package com.barbosa.desafio_backend_nubank_JAVA.exceptionshandler;

import com.barbosa.desafio_backend_nubank_JAVA.exceptions.ClientAlreadyExistsException;
import com.barbosa.desafio_backend_nubank_JAVA.exceptions.ClientNotFoundException;
import com.barbosa.desafio_backend_nubank_JAVA.utils.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ClientExceptionsHandler {

    @ExceptionHandler(ClientAlreadyExistsException.class)
    public ResponseEntity<ExceptionModel> ClientAlreadyExistsExceptionHandler(ClientAlreadyExistsException ex){
        ExceptionModel badRequest = ExceptionModel.builder()
                .title("BAD REQUEST")
                .code(HttpStatus.BAD_REQUEST.value())
                .description(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(badRequest,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ExceptionModel> ClientNotFoundExceptionHandler(ClientNotFoundException ex){
        ExceptionModel badRequest = ExceptionModel.builder()
                .title("BAD REQUEST")
                .code(HttpStatus.BAD_REQUEST.value())
                .description(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(badRequest,HttpStatus.BAD_REQUEST);
    }
}
