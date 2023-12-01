package com.example.socialdanceserver.config.security.jwt;

import com.example.socialdanceserver.api.exceptions.jwt.JwtAuthenticationException;
import com.example.socialdanceserver.persistence.entity.CredentialEntity;
import com.example.socialdanceserver.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class JwtCredentialDetailsService implements UserDetailsService {

    @Lazy
    @Autowired
    private CredentialService credentialService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String[] emailPassword = username.split("\n");
        String email = emailPassword[0];
        String password = "";
        if (emailPassword.length > 1) {
            password = emailPassword[1];
        }
        CredentialEntity credentialEntity = credentialService.getByEmail(email);
        try {
            credentialService.checkCredential(credentialEntity, password);
        }catch (BadCredentialsException | AuthenticationCredentialsNotFoundException e){
            throw new JwtAuthenticationException("Wrong token.");
        }

        return new User(credentialEntity.getEmail(), credentialEntity.getPassword(), new ArrayList<>());
    }
}
