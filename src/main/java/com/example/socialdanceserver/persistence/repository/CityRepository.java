package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.location.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {

    List<CityEntity> findByNameStartingWithIgnoreCaseOrderByName(String name);

    List<CityEntity> findByNameStartingWithIgnoreCaseAndCountry_NameStartingWithIgnoreCaseOrderByName(String name, String countryName);

}
