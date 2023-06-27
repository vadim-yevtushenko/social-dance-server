package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.DancerDto;

public interface CredentialService {

    DancerDto login (String email, String password);

    DancerDto registration (String email, String password, DancerDto dancerDto);

    void changePassword(String email, String password);

    String changeEmail(String oldEmail, String newEmail);

}
