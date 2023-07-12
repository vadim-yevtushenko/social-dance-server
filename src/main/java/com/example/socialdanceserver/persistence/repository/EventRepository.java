package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.DancerEntity;
import com.example.socialdanceserver.persistence.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {

    Set<EventEntity> findDistinctByOrganizersInOrderByCreated(Set<DancerEntity> organizers);

    Set<EventEntity> findDistinctBySchoolOrganizerIdOrderByCreated(UUID id);
}
