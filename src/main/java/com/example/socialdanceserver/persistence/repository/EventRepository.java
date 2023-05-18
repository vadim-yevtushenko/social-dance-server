package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Set;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {

    EventEntity findDistinctEventEntityById(UUID id);

    Set<EventEntity> findDistinctBySchoolOrganizerId(UUID id);
}
