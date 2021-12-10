package com.example.socialdanceserver.repository;


import com.example.socialdanceserver.model.LoginPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoginPasswordRepository extends JpaRepository<LoginPassword, Integer> {

    @Query(value = "select * from login_password" +
            "where email = :email"
            , nativeQuery = true)
    LoginPassword findByEmail(String email);
}
