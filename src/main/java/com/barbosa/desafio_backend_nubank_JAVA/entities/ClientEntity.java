package com.barbosa.desafio_backend_nubank_JAVA.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "client_tb")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(unique = true,nullable = false)
    private String cnpj;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonManagedReference
    private List<ContactEntity> contacts;

    @Column
    private LocalDateTime createdAt;
}
