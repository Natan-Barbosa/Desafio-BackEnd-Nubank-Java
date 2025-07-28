package com.barbosa.desafio_backend_nubank_JAVA.controller;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ClientEntity;
import com.barbosa.desafio_backend_nubank_JAVA.entities.ContactEntity;
import com.barbosa.desafio_backend_nubank_JAVA.service.Contacts.ContactsCreateDto;
import com.barbosa.desafio_backend_nubank_JAVA.service.Contacts.ContactsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactsController.class)
class ContactsControllerTest {

    private final ContactsCreateDto invalidInput = ContactsCreateDto.builder()
            .contactName(null)
            .contactNumber(null)
            .clientID(null)
            .build();

    private final ContactsCreateDto validInput = ContactsCreateDto.builder()
            .contactName("Fake Name")
            .contactNumber("12345678")
            .clientID(1L)
            .build();

    private final ContactEntity output = ContactEntity.builder()
            .id(1L)
            .contactNumber("1234567890")
            .contactName("Fake Name")
            .createdAt(LocalDateTime.now())
            .client(new ClientEntity())
            .build();

    @MockitoBean
    ContactsService contactsService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    @DisplayName("Should Throws Status 400")
    void create_Case_1() throws Exception {

        String input = objectMapper.writeValueAsString(invalidInput);

        Mockito.when(contactsService.create(invalidInput)).thenReturn(output);

        mockMvc.perform(post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.contactName").exists())
                .andExpect(jsonPath("$.errors.contactNumber").exists())
                .andExpect(jsonPath("$.errors.clientID").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("Should Create With Success")
    void create_Case_2() throws Exception {

        String input = objectMapper.writeValueAsString(validInput);

        Mockito.when(contactsService.create(validInput)).thenReturn(output);

        mockMvc.perform(post("/contacts")
                        .contentType(MediaType.APPLICATION_JSON).content(input))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.errors").doesNotExist())
                .andDo(print());
    }
}