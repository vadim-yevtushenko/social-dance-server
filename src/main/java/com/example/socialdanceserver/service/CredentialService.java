package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.persistence.entity.CredentialEntity;

public interface CredentialService {

    DancerDto login (String email, String password);

    DancerDto registration (String email, String password, DancerDto dancerDto);

    void changePassword(String email, String newPassword, String oldPassword);

    String changeEmail(String oldEmail, String newEmail);

    void resetPassword(String email);

    CredentialEntity getByEmail(String email);

    void checkCredential(CredentialEntity credential, String password);

}
