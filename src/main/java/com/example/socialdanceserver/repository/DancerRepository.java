package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.Dancer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DancerRepository extends AbstractBaseEntityRepository {

    @Query(value = "select * from abstract_base_entity abe " +
            "left join dancers d on abe.id = d.id " +
            "left join ratings r on abe.id = r.abstract_base_entity_id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where type_entity = 'DANCER'",
            nativeQuery = true)
    List<Dancer> findAllByType();

    @Query(value = "select abe.id from abstract_base_entity abe " +
            "left join dancers d on abe.id = d.id " +
            "left join login_password lp on d.login_password_id = lp.id " +
            "where lp.email = :email",
            nativeQuery = true)
    Integer checkSignUpByEmail(@Param("email") String email);

    @Query(value = "select abe.id from abstract_base_entity abe " +
            "left join dancers d on abe.id = d.id " +
            "left join login_password lp on d.login_password_id = lp.id " +
            "where lp.email = :email and lp.password = :password",
            nativeQuery = true)
    Integer checkSignInByEmailAndPassword(@Param("email") String email,
                                      @Param("password") String password);
}

