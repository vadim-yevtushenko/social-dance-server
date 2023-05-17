package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.DancerEntity;
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

}

