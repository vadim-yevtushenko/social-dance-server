package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.DanceEntity;
import com.example.socialdanceserver.persistence.entity.DancerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface DancerRepository extends JpaRepository<DancerEntity, UUID> {

    DancerEntity findDistinctDancerEntityById(UUID id);

    @Query("select dance from DanceEntity dance")
    List<DanceEntity> getAllDances();

}

