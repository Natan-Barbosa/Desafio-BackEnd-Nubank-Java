package com.barbosa.desafio_backend_nubank_JAVA.service.Client;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ClientEntity;
import com.barbosa.desafio_backend_nubank_JAVA.exceptions.ClientAlreadyExistsException;
import com.barbosa.desafio_backend_nubank_JAVA.exceptions.ClientNotFoundException;
import com.barbosa.desafio_backend_nubank_JAVA.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public ClientEntity create(CreateClientDto clientDto) {
        boolean clientExists = clientRepository.existsBycnpj(clientDto.getCnpj());
        if (clientExists) {
            throw new ClientAlreadyExistsException("Client With This CNPJ Already Exists!");
        }

        ClientEntity client = ClientEntity.builder()
                .name(clientDto.getName())
                .cnpj(clientDto.getCnpj())
                .createdAt(clientDto.getCreatedAt())
                .build();
        ClientEntity savedClient = clientRepository.save(client);
        return savedClient;
    }

    public List<ClientEntity> get() {
        List<ClientEntity> allClients = clientRepository.findAll();
        return allClients;
    }

    public ClientEntity getClientById(Long id) {
        ClientEntity client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("The Sended Client Not Exists"));
        return client;
    }
}
