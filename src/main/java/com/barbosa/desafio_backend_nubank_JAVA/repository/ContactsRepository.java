package com.barbosa.desafio_backend_nubank_JAVA.repository;

import com.barbosa.desafio_backend_nubank_JAVA.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<ContactEntity,Long> {
    boolean existsBycontactNumber(String contactNumber);
}
