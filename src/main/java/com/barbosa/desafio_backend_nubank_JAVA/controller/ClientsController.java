package com.barbosa.desafio_backend_nubank_JAVA.controller;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ClientEntity;
import com.barbosa.desafio_backend_nubank_JAVA.service.Client.ClientService;
import com.barbosa.desafio_backend_nubank_JAVA.service.Client.CreateClientDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ClientEntity>> get() {
        List<ClientEntity> serviceResponse = clientService.get();
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientEntity> getById(@PathVariable @Valid @NotNull Long id) {
        ClientEntity serviceResponse = clientService.getClientById(id);
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

}
