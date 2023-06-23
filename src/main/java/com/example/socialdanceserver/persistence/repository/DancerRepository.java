package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.DancerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface DancerRepository extends JpaRepository<DancerEntity, UUID> {

}

