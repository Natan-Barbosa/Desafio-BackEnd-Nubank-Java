package com.barbosa.desafio_backend_nubank_JAVA.service.Client;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateClientDto {
    @NotEmpty
    @Size(min = 14,max = 14)
    private String cnpj;

    @NotEmpty
    private String name;

    private final LocalDateTime createdAt = LocalDateTime.now();

}
