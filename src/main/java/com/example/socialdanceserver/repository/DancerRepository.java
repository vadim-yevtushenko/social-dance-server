package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.DancerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Set;
import java.util.UUID;

@Repository
public interface DancerRepository extends JpaRepository<DancerEntity, UUID> {

    DancerEntity findDistinctDancerEntityById(UUID id);

    @Query("select distinct dancer from DancerEntity dancer order by dancer.name, dancer.lastName")
    Set<DancerEntity> findDistinctAllDancers();

    Set<DancerEntity> findDistinctByNameStartingWithIgnoreCaseOrderByLastName(String name);

    Set<DancerEntity> findDistinctByLastNameStartingWithIgnoreCaseOrderByName(String lastName);

    Set<DancerEntity> findDistinctByNameStartingWithIgnoreCaseAndLastNameStartingWithIgnoreCase(String name, String lastName);

    Set<DancerEntity> findDistinctByContactInfo_CityStartingWithIgnoreCaseOrderByNameAscLastNameAsc(String city);

}

