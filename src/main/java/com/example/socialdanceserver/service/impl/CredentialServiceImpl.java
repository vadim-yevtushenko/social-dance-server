package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.persistence.entity.CredentialEntity;
import com.example.socialdanceserver.persistence.entity.DancerEntity;
import com.example.socialdanceserver.persistence.repository.CredentialRepository;
import com.example.socialdanceserver.service.CredentialService;
import com.example.socialdanceserver.service.DancerService;
import com.example.socialdanceserver.service.EmailService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.mail.internet.InternetAddress;
import java.security.SecureRandom;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CredentialServiceImpl extends BaseService implements CredentialService {

    private static final String PASSWORD_VALIDATION_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,25}$";

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private DancerService dancerService;

    @Autowired
    private EmailService emailService;

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
        if (!isValidPassword(password)) {
            throw new BadCredentialsException("The password must contain uppercase and lowercase letters and numbers.");
        }
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
        if (!isValidPassword(newPassword)) {
            throw new BadCredentialsException("The password must contain uppercase and lowercase letters and numbers.");
        }
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

    @SneakyThrows
    @Override
    public void resetPassword(String email) {
        if (!isUsedEmail(email)){
            throw new BadCredentialsException(String.format("Dancer with address %s does not exist.", email));
        }
        String newPassword = generatePassword();
        String message = "Password reset successful.\n\nNew password generated: " + newPassword;

        CredentialEntity credentialEntity = credentialRepository.findCredentialEntityByEmail(email);
        credentialEntity.setPassword(passwordEncoder.encode(newPassword));
        credentialRepository.save(credentialEntity);

        String fullName = credentialEntity.getDancer().getName() + " " + credentialEntity.getDancer().getLastName();
        InternetAddress internetAddress = new InternetAddress(email, fullName);

        emailService.sendEmails(List.of(internetAddress), "Reset password", message);
    }

    @Override
    public CredentialEntity getByEmail(String email) {
        return credentialRepository.findCredentialEntityByEmail(email);
    }

    @Override
    public void checkCredential(CredentialEntity credential, String password) {
        if (credential != null) {
            if (!passwordEncoder.matches(password, credential.getPassword())) {
                throw new BadCredentialsException("Current password is wrong.");
            }
        } else {
            throw new AuthenticationCredentialsNotFoundException("No credentials found to use.");
        }
    }

    private String generatePassword() {
        String charsStr = "abcdefghijklmnopqrstuvwxyz" +
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "0123456789";
        char[] characters = charsStr.toCharArray();
        return RandomStringUtils.random(8, 0, characters.length, false, false, characters, new SecureRandom());
    }

    private boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_VALIDATION_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    private boolean isUsedEmail(String email) {
        CredentialEntity credentialEntity = credentialRepository.findCredentialEntityByEmail(email);
        return credentialEntity != null;
    }

}
