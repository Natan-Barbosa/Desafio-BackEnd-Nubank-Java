package com.barbosa.desafio_backend_nubank_JAVA.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "client_tb")
public class ClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column
    private String cnpj;

    @Column
    private String name;

    @OneToMany(mappedBy = "client", cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    private List<ContactEntity> contacts;

    @Column
    private LocalDateTime createdAt;
}
