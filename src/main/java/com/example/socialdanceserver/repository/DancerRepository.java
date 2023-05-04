package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.DancerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DancerRepository extends JpaRepository<DancerEntity, UUID> {

    List<DancerEntity> findByNameStartingWithIgnoreCaseOrderByLastName(String name);

    List<DancerEntity> findByLastNameStartingWithIgnoreCaseOrderByName(String lastName);

    List<DancerEntity> findByNameStartingWithIgnoreCaseAndLastNameStartingWithIgnoreCase(String name, String lastName);

    List<DancerEntity> findByContactInfo_CityContainsIgnoreCaseOrderByNameAscLastNameAsc(String city);

}

