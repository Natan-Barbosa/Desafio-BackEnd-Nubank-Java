package com.barbosa.desafio_backend_nubank_JAVA.controller;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ContactEntity;
import com.barbosa.desafio_backend_nubank_JAVA.service.Contacts.ContactsCreateDto;
import com.barbosa.desafio_backend_nubank_JAVA.service.Contacts.ContactsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("contacts")
@RestController
public class ContactsController {

    @Autowired
    private ContactsService contactsService;

    @PostMapping
    public ResponseEntity<ContactEntity> create(@RequestBody @Valid ContactsCreateDto createDto) {
        ContactEntity serviceResponse = contactsService.create(createDto);
        return new ResponseEntity<>(serviceResponse, HttpStatus.CREATED);
    }
}
