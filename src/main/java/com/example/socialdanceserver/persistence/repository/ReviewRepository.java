package com.example.socialdanceserver.persistence.repository;

import com.example.socialdanceserver.persistence.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<ReviewEntity, UUID> {

    List<ReviewEntity> findReviewEntitiesBySchoolIdAndDancerId(UUID schoolId, UUID dancerId);

    List<ReviewEntity> findReviewEntitiesBySchoolId(UUID schoolId);

}
