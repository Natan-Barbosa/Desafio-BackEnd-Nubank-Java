package com.barbosa.desafio_backend_nubank_JAVA.repository;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Long, ClientEntity> {
}
