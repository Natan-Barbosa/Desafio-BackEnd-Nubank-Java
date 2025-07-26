package com.barbosa.desafio_backend_nubank_JAVA.service.Client;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ClientEntity;
import com.barbosa.desafio_backend_nubank_JAVA.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientEntity create(CreateClientDto clientDto) {
        ClientEntity client = ClientEntity.builder()
                .name(clientDto.getName())
                .cnpj(clientDto.getCnpj())
                .createdAt(clientDto.getCreatedAt())
                .build();
        ClientEntity savedClient = clientRepository.save(client);
        return savedClient;
    }
}
