package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.persistence.entity.CredentialEntity;
import java.util.Map;
import java.util.UUID;

public interface CredentialService {

    Map<String, Object> login (String email, String password);

    Map<String, Object> registration (String email, String password, DancerDto dancerDto);

    String changePassword(String email, String newPassword, String oldPassword);

    String changeEmail(String oldEmail, String newEmail, String password);

    void resetPassword(String email);

    CredentialEntity getByEmail(String email);

    void checkCredential(CredentialEntity credential, String password);

    void checkPassword(UUID dancerId, String password);

}
