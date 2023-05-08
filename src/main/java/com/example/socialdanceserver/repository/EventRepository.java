package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {

    List<EventEntity> findByNameContainsIgnoreCaseOrderByContactInfo_CityAsc(String name);

    List<EventEntity> findByContactInfo_CityStartingWithIgnoreCaseOrderByNameAsc(String city);

    List<EventEntity> findBySchoolOrganizerId(UUID id);
}
