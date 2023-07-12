package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.RatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, UUID> {

    List<RatingEntity> findRatingEntitiesBySchoolId(UUID schoolId);

    RatingEntity findRatingEntityBySchoolIdAndDancerId(UUID schoolId, UUID dancerId);

}
