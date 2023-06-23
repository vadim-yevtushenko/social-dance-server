package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity, UUID> {

}
