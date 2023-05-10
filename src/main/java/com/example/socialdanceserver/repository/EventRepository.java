package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Set;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {

    EventEntity findDistinctEventEntityById(UUID id);

    @Query("select distinct event from EventEntity event order by event.name")
    Set<EventEntity> findDistinctAllEvents();

    Set<EventEntity> findDistinctByNameContainsIgnoreCaseOrderByContactInfo_CityAsc(String name);

    Set<EventEntity> findDistinctByContactInfo_CityStartingWithIgnoreCaseOrderByNameAsc(String city);

    Set<EventEntity> findDistinctBySchoolOrganizerId(UUID id);
}
