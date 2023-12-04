package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CredentialRepository extends JpaRepository<CredentialEntity, UUID> {

    CredentialEntity findCredentialEntityByEmail(String email);

    @Query(value = "select c.password from credential c where c.dancer_id=:id",
            nativeQuery = true)
    String fetchPasswordByDancerId(@Param("id") UUID id);

}
