package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.CredentialEntity;
import com.example.socialdanceserver.model.DancerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CredentialRepository extends JpaRepository<CredentialEntity, UUID> {

    List<CredentialEntity> findByDancer(DancerEntity dancer);

}
