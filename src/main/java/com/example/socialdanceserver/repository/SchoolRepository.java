package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.model.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface SchoolRepository extends JpaRepository<SchoolEntity, UUID> {

    List<SchoolEntity> findByContactInfo_CityStartingWithIgnoreCaseOrderByNameAsc(String city);

//    @Query(value = "select * from abstract_base_entity abe " +
//            "left join schools s on abe.id = s.id " +
//            "left join entity_info ei on abe.entity_info_id = ei.id " +
//            "where s.owner_id = :id"
//            , nativeQuery = true)
//    List<SchoolEntity> findAllByOwnerId(@Param("id") UUID id);

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "update ratings set rating = :rating " +
//            "where reviewer_id = :reviewerId"
//            , nativeQuery = true)
//    void saveRating(@Param("reviewerId") UUID reviewerId, @Param("rating") int rating);
//
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "update reviews set review = :review, date_time = :dateTime " +
//            "where id = :id"
//            , nativeQuery = true)
//    void saveReview(@Param("id") UUID id, @Param("review") String review,
//                    @Param("dateTime") ZonedDateTime dateTime);

//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "insert into ratings (abstract_base_entity_id, reviewer_id, rating) " +
//            "values (:abstractBaseEntityId, :reviewerId, :rating)"
//            , nativeQuery = true)
//    void createRating(@Param("abstractBaseEntityId") UUID abstractBaseEntityId,
//                      @Param("reviewerId") UUID reviewerId, @Param("rating") int rating);
//
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query(value = "insert into reviews (abstract_base_entity_id, school_id, review, date_time, incognito) " +
//            "values (:abstractBaseEntityId, :schoolId, :review, :dateTime, :incognito)"
//            , nativeQuery = true)
//    void createReview(@Param("abstractBaseEntityId") UUID abstractBaseEntityId,
//                      @Param("schoolId") UUID schoolId, @Param("review") String review,
//                      @Param("dateTime") ZonedDateTime dateTime,
//                      @Param("incognito") Boolean incognito);

}
