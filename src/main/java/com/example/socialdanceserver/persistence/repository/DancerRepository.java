package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.DancerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface DancerRepository extends JpaRepository<DancerEntity, UUID> {

    List<DancerEntity> findDancerEntitiesByContactInfo_City(String city);

    @Query(value = "select c.email from credential c " +
            "join dancer d on c.dancer_id=d.id " +
            "join contact_info ci on d.contact_info_id=ci.id " +
            "where ci.city=:city",
            nativeQuery = true)
    List<String> fetchEmailsByCity(@Param("city") String city);

}

