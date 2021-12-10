package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.model.LoginPassword;
import com.example.socialdanceserver.repository.LoginPasswordRepository;
import com.example.socialdanceserver.service.LoginPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoginPasswordImpl implements LoginPasswordService {

    @Autowired
    private LoginPasswordRepository repository;


    @Override
    public List<LoginPassword> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional<LoginPassword> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public LoginPassword getByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public LoginPassword save(LoginPassword loginPassword) {
        return repository.save(loginPassword);
    }

    @Override
    public void deleteByDancerId(int id) {
        repository.deleteById(id);
    }
}
