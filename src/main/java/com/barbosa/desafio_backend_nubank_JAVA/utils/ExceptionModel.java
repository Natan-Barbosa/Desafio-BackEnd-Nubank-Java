package com.barbosa.desafio_backend_nubank_JAVA.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionModel {
    private String title;
    private String description;
    private Map<String, String> errors;
    private Integer code;
    private LocalDateTime timeStamp;
}
