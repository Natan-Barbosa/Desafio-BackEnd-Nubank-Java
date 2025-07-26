package com.barbosa.desafio_backend_nubank_JAVA.service.Contacts;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ClientEntity;
import com.barbosa.desafio_backend_nubank_JAVA.entities.ContactEntity;
import com.barbosa.desafio_backend_nubank_JAVA.exceptions.ClientNotFoundException;
import com.barbosa.desafio_backend_nubank_JAVA.exceptions.ContactAlreadyExistsException;
import com.barbosa.desafio_backend_nubank_JAVA.repository.ClientRepository;
import com.barbosa.desafio_backend_nubank_JAVA.repository.ContactsRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ContactsServiceTest {

    @Mock
    private ContactsRepository contactsRepository;

    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    ContactsService contactsService;

    @Test
    @DisplayName("Should Return ClientNotFoundException")
    void create_Case_1() {
        ContactsCreateDto fakeContactDto = FakeData.fakeContactDto();
        try (MockedStatic<TransactionSynchronizationManager> mockTransaction = Mockito.mockStatic(TransactionSynchronizationManager.class)) {

            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);
            Mockito.when(clientRepository.findById(fakeContactDto.getClientID())).thenReturn(Optional.ofNullable(null));

            Assertions.assertThrows(ClientNotFoundException.class, () -> contactsService.create(fakeContactDto));
            Mockito.verify(contactsRepository, Mockito.never()).existsBycontactNumber(fakeContactDto.getContactNumber());
            Mockito.verify(contactsRepository, Mockito.never()).save(ArgumentMatchers.any());
        }
    }

    @Test
    @DisplayName("Should Return ContactAlreadyExistsException")
    void create_Case_2() {
        ContactsCreateDto fakeContactDto = FakeData.fakeContactDto();
        ClientEntity fakeClient = FakeData.fakeClient();

        try (MockedStatic<TransactionSynchronizationManager> mockTransaction = Mockito.mockStatic(TransactionSynchronizationManager.class)) {

            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);
            Mockito.when(clientRepository.findById(fakeContactDto.getClientID())).thenReturn(Optional.ofNullable(fakeClient));
            Mockito.when(contactsRepository.existsBycontactNumber(fakeContactDto.getContactNumber())).thenReturn(true);

            Assertions.assertThrows(ContactAlreadyExistsException.class, () -> contactsService.create(fakeContactDto));
            Mockito.verify(contactsRepository, Mockito.never()).save(ArgumentMatchers.any());
        }
    }

    @Test
    @DisplayName("Should Create With Success")
    void create_Case_3() {
        ContactsCreateDto fakeContactDto = FakeData.fakeContactDto();
        ClientEntity fakeClient = FakeData.fakeClient();
        ContactEntity fakeContact = FakeData.fakeContact(fakeClient);

        try (MockedStatic<TransactionSynchronizationManager> mockTransaction = Mockito.mockStatic(TransactionSynchronizationManager.class)) {

            mockTransaction.when(TransactionSynchronizationManager::isSynchronizationActive).thenReturn(true);
            Mockito.when(clientRepository.findById(fakeContactDto.getClientID())).thenReturn(Optional.ofNullable(fakeClient));
            Mockito.when(contactsRepository.existsBycontactNumber(fakeContactDto.getContactNumber())).thenReturn(false);
            Mockito.when(contactsRepository.save(ArgumentMatchers.any())).thenReturn(fakeContact);

            ContactEntity serviceResponse = contactsService.create(fakeContactDto);

            Assertions.assertNotNull(serviceResponse);
            Mockito.verify(contactsRepository).existsBycontactNumber(fakeContactDto.getContactNumber());
            Mockito.verify(clientRepository).findById(ArgumentMatchers.any());
            Mockito.verify(contactsRepository).save(ArgumentMatchers.any());
        }
    }
}