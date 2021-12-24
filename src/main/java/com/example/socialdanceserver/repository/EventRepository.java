package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends AbstractBaseEntityRepository {

    @Query(value = "select * from abstract_base_entity abe " +
            "left join events e on abe.id = e.id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where type_entity = 'EVENT' and date_finish_event >= current_date " +
            "order by date_publication desc",
            nativeQuery = true)
    List<Event> findAllByType();

    @Query(value = "select * from abstract_base_entity abe " +
            "left join events e on abe.id = e.id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where type_entity = 'EVENT' and date_finish_event >= current_date and lower(ei.city) = lower(:city) " +
            "order by date_publication desc",
            nativeQuery = true)
    List<Event> findAllByCity(@Param("city") String city);

    @Query(value = "select * from abstract_base_entity abe " +
            "left join events e on abe.id = e.id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where e.owner_id = :id",
            nativeQuery = true)
    List<Event> findAllByOwnerId(@Param("id") int id);
}
