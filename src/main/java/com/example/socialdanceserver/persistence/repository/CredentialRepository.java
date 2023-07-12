package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CredentialRepository extends JpaRepository<CredentialEntity, UUID> {

    CredentialEntity findCredentialEntityByEmail(String email);

}
