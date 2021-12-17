package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.School;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends AbstractBaseEntityRepository{

    @Query(value = "select * from abstract_base_entity abe " +
            "left join schools s on abe.id = s.id " +
            "left join ratings on abe.id = ratings.abstract_base_entity_id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "left join reviews rv on rv.school_id = s.id " +
            "where type_entity = 'SCHOOL'"
            ,nativeQuery = true)
    List<School> findAllByType();

    @Query(value = "select * from abstract_base_entity abe " +
            "left join schools s on abe.id = s.id " +
            "left join ratings on abe.id = ratings.abstract_base_entity_id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
//            "left join reviews rv on rv.school_id = s.id " +
            "where s.owner_id = :id"
            ,nativeQuery = true)
    List<School> findAllByOwnerId(@Param("id") int id);
}
