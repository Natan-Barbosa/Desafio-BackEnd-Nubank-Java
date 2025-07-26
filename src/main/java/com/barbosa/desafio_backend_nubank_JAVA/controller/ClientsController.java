package com.barbosa.desafio_backend_nubank_JAVA.controller;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ClientEntity;
import com.barbosa.desafio_backend_nubank_JAVA.service.Client.ClientService;
import com.barbosa.desafio_backend_nubank_JAVA.service.Client.CreateClientDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("client")
@RestController
public class ClientsController {

    @Autowired
    ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientEntity> create(@RequestBody @Valid CreateClientDto dto) {
        ClientEntity serviceResponse = clientService.create(dto);
        return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
    }

}
