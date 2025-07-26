package com.barbosa.desafio_backend_nubank_JAVA.service.Client.Create;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ClientEntity;
import com.barbosa.desafio_backend_nubank_JAVA.exceptions.ClientAlreadyExistsException;
import com.barbosa.desafio_backend_nubank_JAVA.repository.ClientRepository;
import com.barbosa.desafio_backend_nubank_JAVA.service.Client.ClientService;
import com.barbosa.desafio_backend_nubank_JAVA.service.Client.CreateClientDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    @DisplayName("Should Throws ClientAlreadyExistsException")
    void create_Case_1() {
        CreateClientDto fakeDto = FakeData.fakeDto();
        try (MockedStatic<TransactionSynchronizationManager> mockTransaction = Mockito.mockStatic(TransactionSynchronizationManager.class)) {

            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);
            Mockito.when(clientRepository.existsBycnpj(fakeDto.getCnpj())).thenReturn(true);

            Assertions.assertThrows(ClientAlreadyExistsException.class, () -> clientService.create(fakeDto));

            Mockito.verify(clientRepository, Mockito.never()).save(ArgumentMatchers.any());
        }

    }

    @Test
    @DisplayName("Should Create With Success")
    void create_Case_2() {
        CreateClientDto fakeDto = FakeData.fakeDto();
        ClientEntity fakeClient = FakeData.fakeClientEntity(fakeDto);

        try (MockedStatic<TransactionSynchronizationManager> mockTransaction = Mockito.mockStatic(TransactionSynchronizationManager.class)) {
            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);
            Mockito.when(clientRepository.existsBycnpj(fakeDto.getCnpj())).thenReturn(false);
            Mockito.when(clientRepository.save(ArgumentMatchers.any())).thenReturn(fakeClient);

            ClientEntity serviceResponse = clientService.create(fakeDto);
            Assertions.assertNotNull(serviceResponse);
            Mockito.verify(clientRepository).save(ArgumentMatchers.any());
        }
    }
}