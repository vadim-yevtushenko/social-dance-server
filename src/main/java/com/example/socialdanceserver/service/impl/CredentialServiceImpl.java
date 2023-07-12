package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.persistence.entity.CredentialEntity;
import com.example.socialdanceserver.persistence.entity.DancerEntity;
import com.example.socialdanceserver.persistence.repository.CredentialRepository;
import com.example.socialdanceserver.service.CredentialService;
import com.example.socialdanceserver.service.DancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CredentialServiceImpl extends BaseService implements CredentialService {

//    private static final String PASSWORD_VALIDATION_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,25}$";

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private DancerService dancerService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public DancerDto login(String email, String password) {
        CredentialEntity credentialEntity = credentialRepository.findCredentialEntityByEmail(email);
        checkCredential(credentialEntity, password);

        return  dancerService.getDancerDtoFromEntity(credentialEntity.getDancer());
    }

    @Override
    public DancerDto registration(String email, String password, DancerDto dancerDto) {
        DancerEntity dancerEntity = mapper.map(dancerDto, DancerEntity.class);

        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setEmail(email);
        credentialEntity.setPassword(passwordEncoder.encode(password));
        credentialEntity.setDancer(dancerEntity);
        credentialEntity = credentialRepository.save(credentialEntity);

        return mapper.map(credentialEntity.getDancer(), DancerDto.class);
    }

    @Override
    public void changePassword(String email, String newPassword, String oldPassword) {
        CredentialEntity credentialEntity = credentialRepository.findCredentialEntityByEmail(email);
        checkCredential(credentialEntity, oldPassword);
        credentialEntity.setPassword(passwordEncoder.encode(newPassword));
        credentialRepository.save(credentialEntity);
    }

    @Override
    public String changeEmail(String oldEmail, String newEmail) {
        CredentialEntity credentialEntity = credentialRepository.findCredentialEntityByEmail(oldEmail);
        credentialEntity.setEmail(newEmail);
        credentialEntity = credentialRepository.save(credentialEntity);

        return credentialEntity.getEmail();
    }

    private void checkCredential(CredentialEntity credential, String password) {
        if (credential != null) {
            if (!passwordEncoder.matches(password, credential.getPassword())) {
                throw new BadCredentialsException("Current password is wrong.");
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException("No credentials found to use.");
        }
    }

}
