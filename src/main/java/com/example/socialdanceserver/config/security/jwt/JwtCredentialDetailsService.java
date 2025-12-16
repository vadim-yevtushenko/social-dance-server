package com.example.socialdanceserver.config.security.jwt;

import com.example.socialdanceserver.api.exceptions.notfound.NotFoundException;
import com.example.socialdanceserver.persistence.entity.CredentialEntity;
import com.example.socialdanceserver.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

        CredentialEntity credentialEntity = credentialService.getByEmail(username);

        if (credentialEntity == null) {
            throw new NotFoundException("User with email: " + username + " not found.");
        }

        return new User(credentialEntity.getEmail(), credentialEntity.getPassword(), new ArrayList<>());
    }
}
