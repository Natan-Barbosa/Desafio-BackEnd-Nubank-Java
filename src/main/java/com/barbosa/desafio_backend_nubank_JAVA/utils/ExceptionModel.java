package com.barbosa.desafio_backend_nubank_JAVA.utils;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionModel {
    private String title;
    private String description;
    private Integer code;
    private LocalDateTime timeStamp;
}
