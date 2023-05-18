package com.example.socialdanceserver.persistence.dao;

import com.example.socialdanceserver.persistence.entity.EventEntity;
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
public class EventDao extends EntityDao<EventEntity>{

    public static final String EVENT_NAME = "event.name";

    public static final String EVENT_CONTACT_INFO_CITY = "event.contactInfo.city";

    @Override
    protected List<Order> buildOrders(List<String> orders, CriteriaBuilder builder, Root<EventEntity> root) {

        return orders.stream()
                .map(order -> builder.asc(root.get(order)))
                .collect(Collectors.toList());
    }

    @Override
    protected List<Predicate> buildPredicates(Map<String, String> mapPredicates, CriteriaBuilder builder, Root<EventEntity> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!mapPredicates.get(EVENT_NAME).isBlank()){
            predicates.add(buildLikeContainingStringIgnoringCase(builder, root.get("name"), mapPredicates.get(EVENT_NAME)));
        }

        if (!mapPredicates.get(EVENT_CONTACT_INFO_CITY).isBlank()){
            predicates.add(buildLikeContainingStringIgnoringCase(builder, root.join("contactInfo").get("city"), mapPredicates.get(EVENT_CONTACT_INFO_CITY)));
        }

        return predicates;
    }

    public Map<String, String> getMapPredicates(String name, String city) {
        Map<String, String> mapPredicates = new HashMap<>();
        mapPredicates.put(EVENT_NAME, name);
        mapPredicates.put(EVENT_CONTACT_INFO_CITY, city);
        return mapPredicates;
    }

}
