package com.example.socialdanceserver.service;

import com.example.socialdanceserver.model.LoginPassword;

import java.util.List;
import java.util.Optional;

public interface LoginPasswordService {

    List<LoginPassword> getAll();

    Optional<LoginPassword> getById(int id);

    LoginPassword getByEmail(String email);

    LoginPassword save(LoginPassword loginPassword);

    void deleteByDancerId(int id);
}
