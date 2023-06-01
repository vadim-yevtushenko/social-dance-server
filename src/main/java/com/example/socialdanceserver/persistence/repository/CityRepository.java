package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.location.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Integer> {
}
