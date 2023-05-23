package com.example.socialdanceserver.persistence.dao;

import com.example.socialdanceserver.persistence.entity.DancerEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class DancerDao extends EntityDao<DancerEntity>{

    public static final String DANCER_NAME = "dancer.name";

    public static final String DANCER_LAST_NAME = "dancer.lastName";

    public static final String DANCER_CONTACT_INFO_CITY = "dancer.contactInfo.city";

//    public static final String DANCER_CONTACT_INFO_EMAIL = "dancer.contactInfo.email";

    @Override
    protected List<Order> buildOrders(List<String> orders, CriteriaBuilder builder, Root<DancerEntity> root) {

        return orders.stream()
                .map(order -> builder.asc(root.get(order)))
                .collect(Collectors.toList());
    }

    @Override
    protected List<Predicate> buildPredicates(Map<String, String> mapPredicates, CriteriaBuilder builder, Root<DancerEntity> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (mapPredicates.get(DANCER_NAME) != null && !mapPredicates.get(DANCER_NAME).isBlank()){
            predicates.add(buildLikeContainingStringIgnoringCase(builder, root.get("name"), mapPredicates.get(DANCER_NAME)));
        }

        if (mapPredicates.get(DANCER_LAST_NAME) != null && !mapPredicates.get(DANCER_LAST_NAME).isBlank()){
            predicates.add(buildLikeContainingStringIgnoringCase(builder, root.get("lastName"), mapPredicates.get(DANCER_LAST_NAME)));
        }

        if (mapPredicates.get(DANCER_CONTACT_INFO_CITY) != null && !mapPredicates.get(DANCER_CONTACT_INFO_CITY).isBlank()){
            predicates.add(buildLikeContainingStringIgnoringCase(builder, root.join("contactInfo").get("city"), mapPredicates.get(DANCER_CONTACT_INFO_CITY)));
        }

//        if (mapPredicates.get(DANCER_CONTACT_INFO_EMAIL) != null && !mapPredicates.get(DANCER_CONTACT_INFO_EMAIL).isBlank()){
//            predicates.add(buildLikeContainingStringIgnoringCase(builder, root.join("contactInfo").get("email"), mapPredicates.get(DANCER_CONTACT_INFO_EMAIL)));
//        }

        return predicates;
    }

    public Map<String, String> getMapPredicates(String name, String lastName, String city) {
        Map<String, String> mapPredicates = new HashMap<>();
        mapPredicates.put(DANCER_NAME, name);
        mapPredicates.put(DANCER_LAST_NAME, lastName);
        mapPredicates.put(DANCER_CONTACT_INFO_CITY, city);
        return mapPredicates;
    }

}
