package com.barbosa.desafio_backend_nubank_JAVA.exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(String message) {
        super(message);
    }
}
