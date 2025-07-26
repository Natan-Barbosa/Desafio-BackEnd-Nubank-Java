package com.barbosa.desafio_backend_nubank_JAVA.service.Contacts;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContactsCreateDto {
    private String contactName;

    private String contactNumber;

    private Long clientID;

    private final LocalDateTime createdAt = LocalDateTime.now();

}
