package com.barbosa.desafio_backend_nubank_JAVA.exceptionshandler;

import com.barbosa.desafio_backend_nubank_JAVA.exceptions.ContactAlreadyExistsException;
import com.barbosa.desafio_backend_nubank_JAVA.utils.ExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ContactExceptionHandler {

    @ExceptionHandler(ContactAlreadyExistsException.class)
    public ResponseEntity<ExceptionModel> ContactAlreadyExistsExceptionHandler(ContactAlreadyExistsException ex) {
        ExceptionModel badRequest = ExceptionModel.builder()
                .title("BAD REQUEST")
                .code(HttpStatus.BAD_REQUEST.value())
                .description(ex.getMessage())
                .timeStamp(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(badRequest, HttpStatus.BAD_REQUEST);
    }
}
