package com.barbosa.desafio_backend_nubank_JAVA.service.Contacts;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ContactsCreateDto {

    @NotEmpty
    private String contactName;

    @NotEmpty
    private String contactNumber;

    @NotNull
    private Long clientID;

    private final LocalDateTime createdAt = LocalDateTime.now();

}
