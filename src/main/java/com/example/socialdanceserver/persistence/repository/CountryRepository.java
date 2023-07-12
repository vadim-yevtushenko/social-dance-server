package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.location.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, Integer> {

    List<CountryEntity> findByNameStartingWithIgnoreCaseOrderByName(String name);

}
