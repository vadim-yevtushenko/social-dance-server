package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.CredentialEntity;
import com.example.socialdanceserver.persistence.entity.DancerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface CredentialRepository extends JpaRepository<CredentialEntity, UUID> {

    List<CredentialEntity> findByDancer(DancerEntity dancer);

    @Query(value = "select d.id from dancer d " +
            "left join credential c on d.credential_id = c.id " +
            "where c.email = :email",
            nativeQuery = true)
    Integer checkSignUpByEmail(@Param("email") String email);

    @Query(value = "select d.id from dancer d " +
            "left join credential c on d.credential_id = c.id " +
            "where c.email = :email and c.password = :password",
            nativeQuery = true)
    Integer checkSignInByEmailAndPassword(@Param("email") String email,
                                          @Param("password") String password);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update credential set password = :password " +
            "where email = :email"
            , nativeQuery = true)
    Integer changePassword(@Param("email") String email,
                           @Param("password") String password);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update credential set email = :newEmail " +
            "where email = :oldEmail"
            , nativeQuery = true)
    Integer changeEmail(@Param("oldEmail") String oldEmail,
                        @Param("newEmail") String newEmail);

}
