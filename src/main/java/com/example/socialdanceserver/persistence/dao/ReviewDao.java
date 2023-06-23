package com.example.socialdanceserver.persistence.dao;

import com.example.socialdanceserver.persistence.entity.ReviewEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ReviewDao extends EntityDao<ReviewEntity>{

    public static final String SCHOOL_ID = "review.schoolId";

    @Override
    protected List<Order> buildOrders(List<String> orders, CriteriaBuilder builder, Root<ReviewEntity> root) {
        return orders.stream()
                .map(order -> builder.asc(root.get(order)))
                .collect(Collectors.toList());
    }

    @Override
    protected List<Predicate> buildPredicates(Map<String, String> mapPredicates, CriteriaBuilder builder, Root<ReviewEntity> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (mapPredicates.get(SCHOOL_ID) != null && !mapPredicates.get(SCHOOL_ID).isBlank()){
            predicates.add(builder.equal(root.get("schoolId"), UUID.fromString(mapPredicates.get(SCHOOL_ID))));
        }

        return predicates;
    }

    public Map<String, String> getMapPredicates(String schoolId) {
        Map<String, String> mapPredicates = new HashMap<>();
        mapPredicates.put(SCHOOL_ID, schoolId);
        return mapPredicates;
    }

}
