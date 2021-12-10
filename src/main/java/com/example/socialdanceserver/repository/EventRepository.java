package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends AbstractBaseEntityRepository {

    @Query(value = "select * from abstract_base_entity abe " +
            "left join events e on abe.id = e.id " +
            "left join ratings r on abe.id = r.abstract_base_entity_id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where type_entity = 'EVENT'",
            nativeQuery = true)
    List<Event> findAllByType();
}
