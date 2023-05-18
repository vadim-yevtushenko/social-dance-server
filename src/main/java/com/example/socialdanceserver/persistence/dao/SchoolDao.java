package com.example.socialdanceserver.persistence.dao;

import com.example.socialdanceserver.persistence.entity.SchoolEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class SchoolDao extends EntityDao<SchoolEntity>{

    public static final String SCHOOL_NAME = "school.name";

    public static final String SCHOOL_CONTACT_INFO_CITY = "school.contactInfo.city";


    @Override
    protected List<Order> buildOrders(List<String> orders, CriteriaBuilder builder, Root<SchoolEntity> root) {

        return orders.stream()
                .map(order -> builder.asc(root.get(order)))
                .collect(Collectors.toList());
    }

    @Override
    protected List<Predicate> buildPredicates(Map<String, String> mapPredicates, CriteriaBuilder builder, Root<SchoolEntity> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!mapPredicates.get(SCHOOL_NAME).isBlank()){
            predicates.add(buildLikeContainingStringIgnoringCase(builder, root.get("name"), mapPredicates.get(SCHOOL_NAME)));
        }

        if (!mapPredicates.get(SCHOOL_CONTACT_INFO_CITY).isBlank()){
            predicates.add(buildLikeContainingStringIgnoringCase(builder, root.join("contactInfo").get("city"), mapPredicates.get(SCHOOL_CONTACT_INFO_CITY)));
        }

        return predicates;
    }

    public Map<String, String> getMapPredicates(String name, String city) {
        Map<String, String> mapPredicates = new HashMap<>();
        mapPredicates.put(SCHOOL_NAME, name);
        mapPredicates.put(SCHOOL_CONTACT_INFO_CITY, city);
        return mapPredicates;
    }

}
