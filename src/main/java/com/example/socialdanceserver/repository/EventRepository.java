package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.DancerEntity;
import com.example.socialdanceserver.model.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<EventEntity, UUID> {

    @Query(value = "select * from abstract_base_entity abe " +
            "left join events e on abe.id = e.id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where type_entity = 'EVENT' and date_finish_event >= current_date and lower(ei.city) = lower(:city) " +
            "order by date_publication desc",
            nativeQuery = true)
    List<EventEntity> findAllByCity(@Param("city") String city);

    @Query(value = "select * from abstract_base_entity abe " +
            "left join events e on abe.id = e.id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where e.owner_id = :id",
            nativeQuery = true)
    List<EventEntity> findAllByOwnerId(@Param("id") UUID id);
}
