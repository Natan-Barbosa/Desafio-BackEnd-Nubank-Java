package com.barbosa.desafio_backend_nubank_JAVA.controller.ClientsControllerTest;

import com.barbosa.desafio_backend_nubank_JAVA.controller.ClientsController;
import com.barbosa.desafio_backend_nubank_JAVA.entities.ClientEntity;
import com.barbosa.desafio_backend_nubank_JAVA.service.Client.ClientService;
import com.barbosa.desafio_backend_nubank_JAVA.service.Client.CreateClientDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientsController.class)
class ClientsControllerTest {

    @MockitoBean
    ClientService clientService;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("Should Throws Status 400")
    void create_Case_1() throws Exception {
        CreateClientDto invalidInput = FakeData.invalidFakeClientDto();
        String input = objectMapper.writeValueAsString(invalidInput);
        ClientEntity output = FakeData.fakeClientEntity();

        Mockito.when(clientService.create(invalidInput)).thenReturn(output);

        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.cnpj").exists())
                .andExpect(jsonPath("$.errors.name").exists());
    }

    @Test
    @DisplayName("Should Create With Success")
    void create_Case_2() throws Exception {
        CreateClientDto validInput = FakeData.validFakeClientDto();
        String input = objectMapper.writeValueAsString(validInput);
        ClientEntity output = FakeData.fakeClientEntity();

        Mockito.when(clientService.create(validInput)).thenReturn(output);

        mockMvc.perform(post("/client")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(input))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.errors").doesNotExist());
    }
}