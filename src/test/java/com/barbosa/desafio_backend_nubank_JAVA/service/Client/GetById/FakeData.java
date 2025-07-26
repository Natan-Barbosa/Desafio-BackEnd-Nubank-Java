package com.barbosa.desafio_backend_nubank_JAVA.service.Client.GetById;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ClientEntity;
import com.barbosa.desafio_backend_nubank_JAVA.service.Client.CreateClientDto;

public class FakeData {

    public static CreateClientDto fakeDto() {
        CreateClientDto fakeDto = CreateClientDto.builder()
                .name("Fake Name")
                .cnpj("12345678901234")
                .build();
        return fakeDto;
    }

    public static ClientEntity fakeClientEntity(CreateClientDto clientDto){
        ClientEntity client = ClientEntity.builder()
                .name(clientDto.getName())
                .cnpj(clientDto.getCnpj())
                .createdAt(clientDto.getCreatedAt())
                .build();
        return client;
    }
}
