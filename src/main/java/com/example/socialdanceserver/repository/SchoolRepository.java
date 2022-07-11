package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.SchoolEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SchoolRepository extends AbstractBaseEntityRepository {

    @Query(value = "select * from abstract_base_entity abe " +
            "left join schools s on abe.id = s.id " +
            "left join ratings r on abe.id = r.abstract_base_entity_id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where type_entity = 'SCHOOL' " +
            "order by name"
            , nativeQuery = true)
    List<SchoolEntity> findAllByType();

    @Query(value = "select * from abstract_base_entity abe " +
            "left join schools s on abe.id = s.id " +
            "left join ratings r on abe.id = r.abstract_base_entity_id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
            "where type_entity = 'SCHOOL' and lower(ei.city) = lower(:city) " +
            "order by name"
            , nativeQuery = true)
    List<SchoolEntity> findAllByCity(@Param("city") String city);

    @Query(value = "select * from abstract_base_entity abe " +
            "left join schools s on abe.id = s.id " +
//            "left join ratings on abe.id = ratings.abstract_base_entity_id " +
            "left join entity_info ei on abe.entity_info_id = ei.id " +
//            "left join reviews rv on rv.school_id = s.id " +
            "where s.owner_id = :id"
            , nativeQuery = true)
    List<SchoolEntity> findAllByOwnerId(@Param("id") int id);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update ratings set rating = :rating " +
            "where reviewer_id = :reviewerId"
            , nativeQuery = true)
    void saveRating(@Param("reviewerId") int reviewerId, @Param("rating") int rating);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "update reviews set review = :review, date_time = :dateTime " +
            "where id = :id"
            , nativeQuery = true)
    void saveReview(@Param("id") int id, @Param("review") String review,
                    @Param("dateTime") LocalDateTime dateTime);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into ratings (abstract_base_entity_id, reviewer_id, rating) " +
            "values (:abstractBaseEntityId, :reviewerId, :rating)"
            , nativeQuery = true)
    void createRating(@Param("abstractBaseEntityId") int abstractBaseEntityId,
                      @Param("reviewerId") int reviewerId, @Param("rating") int rating);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "insert into reviews (abstract_base_entity_id, school_id, review, date_time, incognito) " +
            "values (:abstractBaseEntityId, :schoolId, :review, :dateTime, :incognito)"
            , nativeQuery = true)
    void createReview(@Param("abstractBaseEntityId") int abstractBaseEntityId,
                      @Param("schoolId") int schoolId, @Param("review") String review,
                      @Param("dateTime") LocalDateTime dateTime,
                      @Param("incognito") Boolean incognito);

}
