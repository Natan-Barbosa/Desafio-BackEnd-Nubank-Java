package com.barbosa.desafio_backend_nubank_JAVA.service.Client.GetById;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ClientEntity;
import com.barbosa.desafio_backend_nubank_JAVA.exceptions.ClientAlreadyExistsException;
import com.barbosa.desafio_backend_nubank_JAVA.exceptions.ClientNotFoundException;
import com.barbosa.desafio_backend_nubank_JAVA.repository.ClientRepository;
import com.barbosa.desafio_backend_nubank_JAVA.service.Client.ClientService;
import com.barbosa.desafio_backend_nubank_JAVA.service.Client.Create.FakeData;
import com.barbosa.desafio_backend_nubank_JAVA.service.Client.CreateClientDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    @DisplayName("Should Throws ClientNotFoundException")
    void get_Case_1() {
        try (MockedStatic<TransactionSynchronizationManager> mockTransaction = Mockito.mockStatic(TransactionSynchronizationManager.class)) {
            Long id = 1L;
            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);
            Mockito.when(clientRepository.findById(id)).thenReturn(Optional.ofNullable(null));

            Assertions.assertThrows(ClientNotFoundException.class, () -> clientService.getClientById(id));
        }

    }

    @Test
    @DisplayName("Should Return Client")
    void get_Case_2() {
        Long id = 1L;
        CreateClientDto fakeDto = FakeData.fakeDto();
        ClientEntity fakeClient = FakeData.fakeClientEntity(fakeDto);

        try (MockedStatic<TransactionSynchronizationManager> mockTransaction = Mockito.mockStatic(TransactionSynchronizationManager.class)) {
            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);
            Mockito.when(clientRepository.findById(id)).thenReturn(Optional.ofNullable(fakeClient));

            ClientEntity serviceResponse = clientService.getClientById(id);
            Assertions.assertNotNull(serviceResponse);
        }
    }
}