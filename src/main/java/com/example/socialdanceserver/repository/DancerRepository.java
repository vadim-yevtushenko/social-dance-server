package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.Dancer;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DancerRepository extends AbstractBaseEntityRepository {

    @Query(value = "select * from abstract_base_entity abe " +
            "left join dancers d on abe.id = d.id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where type_entity = 'DANCER' " +
            "order by name, surname",
            nativeQuery = true)
    List<Dancer> findAllByType();

    @Query(value = "select * from abstract_base_entity abe " +
            "left join dancers d on abe.id = d.id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where type_entity = 'DANCER' and lower(ei.city) = lower(:city) " +
            "order by name, surname",
            nativeQuery = true)
    List<Dancer> findAllByCity(@Param("city") String city);

    @Query(value = "select * from abstract_base_entity abe " +
            "left join dancers d on abe.id = d.id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where type_entity = 'DANCER' and lower(abe.name) = lower(:name) " +
            "order by surname",
            nativeQuery = true)
    List<Dancer> findAllByName(@Param("name") String name);

    @Query(value = "select * from abstract_base_entity abe " +
            "left join dancers d on abe.id = d.id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where type_entity = 'DANCER' and lower(d.surname) = lower(:surname) " +
            "order by name",
            nativeQuery = true)
    List<Dancer> findAllBySurname(@Param("surname") String surname);

    @Query(value = "select * from abstract_base_entity abe " +
            "left join dancers d on abe.id = d.id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where type_entity = 'DANCER' and lower(abe.name) = lower(:name) and lower(d.surname) = lower(:surname) ",
            nativeQuery = true)
    List<Dancer> findAllByNameAndSurname(@Param("name") String name, @Param("surname") String surname);

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

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update login_password set password = :password " +
            "where email = :email"
            , nativeQuery = true)
    Integer changePassword(@Param("email") String email,
                           @Param("password") String password);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update login_password set email = :newEmail " +
            "where email = :oldEmail"
            , nativeQuery = true)
    Integer changeEmail(@Param("oldEmail") String oldEmail,
                        @Param("newEmail") String newEmail);
}

