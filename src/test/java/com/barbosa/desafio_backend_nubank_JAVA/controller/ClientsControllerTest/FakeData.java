package com.barbosa.desafio_backend_nubank_JAVA.controller.ClientsControllerTest;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ClientEntity;
import com.barbosa.desafio_backend_nubank_JAVA.service.Client.CreateClientDto;

import java.time.LocalDateTime;

public class FakeData {

    public static CreateClientDto validFakeClientDto(){
        CreateClientDto fakeClient = CreateClientDto.builder()
                .name("FakeClient")
                .cnpj("12345678901234")
                .build();
        return fakeClient;
    }

    public static CreateClientDto invalidFakeClientDto(){
        CreateClientDto fakeClient = CreateClientDto.builder()
                .name(null)
                .cnpj("1234567890")
                .build();
        return fakeClient;
    }

    public static ClientEntity fakeClientEntity(){
        ClientEntity fakeClient = ClientEntity.builder()
                .id(1L)
                .name("Fake Name")
                .cnpj("12345678901235")
                .createdAt(LocalDateTime.now()).build();
        return fakeClient;
    }
}
