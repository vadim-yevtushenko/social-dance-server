package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.DancerDto;

public interface CredentialService {

    DancerDto login (String email, String password);

    DancerDto registration (String email, String password, DancerDto dancerDto);

//    Integer checkSignUpByEmail(String email);
//
//    Integer checkSignInByEmailAndPassword(String email, String password);
//
//    Boolean changePassword(String email, String password);
//
//    Boolean changeEmail(String oldEmail, String newEmail);

}
