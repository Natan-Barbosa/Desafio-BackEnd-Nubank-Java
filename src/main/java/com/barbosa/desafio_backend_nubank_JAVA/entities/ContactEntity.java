package com.barbosa.desafio_backend_nubank_JAVA.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Table(name = "contacts_tb")
@Entity
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String contactName;

    @Column
    private String contactNumber;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private ClientEntity client;

    @Column
    private LocalDateTime createdAt;
}
