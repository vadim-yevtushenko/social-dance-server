package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.Dancer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DancerRepository extends AbstractBaseEntityRepository{

    @Query(value = "select * from abstract_base_entity abe " +
            "left join dancers d on abe.id = d.id " +
            "left join ratings r on abe.id = r.abstract_base_entity_id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where type_entity = 'DANCER'",
            nativeQuery = true)
    List<Dancer> findAllByType();
}

