package com.barbosa.desafio_backend_nubank_JAVA.service.Contacts;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ClientEntity;
import com.barbosa.desafio_backend_nubank_JAVA.entities.ContactEntity;
import com.barbosa.desafio_backend_nubank_JAVA.exceptions.ClientNotFoundException;
import com.barbosa.desafio_backend_nubank_JAVA.exceptions.ContactAlreadyExistsException;
import com.barbosa.desafio_backend_nubank_JAVA.repository.ClientRepository;
import com.barbosa.desafio_backend_nubank_JAVA.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactsService {

    @Autowired
    private ContactsRepository contactsRepository;

    @Autowired
    private ClientRepository clientRepository;

    public ContactEntity create(ContactsCreateDto createDto) {
        ClientEntity client = clientRepository.findById(createDto.getClientID())
                .orElseThrow(() -> new ClientNotFoundException("The Sended Client Not Exists"));
        boolean contactExists = contactsRepository.existsBycontactNumber(createDto.getContactNumber());
        if(contactExists){
            throw new ContactAlreadyExistsException("The Contact Already Exists!");
        }

        ContactEntity newContact = ContactEntity.builder()
                .contactName(createDto.getContactName())
                .contactNumber(createDto.getContactNumber())
                .createdAt(createDto.getCreatedAt())
                .client(client)
                .build();

        ContactEntity savedContact = contactsRepository.save(newContact);
        return savedContact;
    }

}
