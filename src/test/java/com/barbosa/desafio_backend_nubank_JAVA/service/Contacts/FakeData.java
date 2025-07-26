package com.barbosa.desafio_backend_nubank_JAVA.service.Contacts;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ClientEntity;
import com.barbosa.desafio_backend_nubank_JAVA.entities.ContactEntity;

import java.time.LocalDateTime;

public class FakeData {

    public static ContactsCreateDto fakeContactDto() {
        ContactsCreateDto fakeContact = ContactsCreateDto.builder()
                .contactName("Fake Name")
                .contactNumber("71998765432")
                .clientID(1L)
                .build();
        return fakeContact;
    }

    public static ClientEntity fakeClient() {
        ClientEntity fakeClient = ClientEntity.builder()
                .id(1L)
                .cnpj("12345678901235")
                .name("Fake Name")
                .createdAt(LocalDateTime.now())
                .build();
        return fakeClient;
    }

    public static ContactEntity fakeContact(ClientEntity client) {
        ContactEntity contact = ContactEntity.builder()
                .client(client)
                .createdAt(LocalDateTime.now())
                .contactNumber("Fake Name")
                .contactNumber("71998765432")
                .id(1L)
                .build();
        return contact;
    }
}
