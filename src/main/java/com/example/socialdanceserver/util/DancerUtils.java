package com.example.socialdanceserver.util;

import com.example.socialdanceserver.dto.DancerTo;
import com.example.socialdanceserver.model.Dancer;
import com.example.socialdanceserver.model.enums.Dance;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class DancerUtils {

    public static List<DancerTo> getDancerTos(List<Dancer> dancerList) {
        return dancerList.stream().map(DancerUtils::createDancerTo).
                collect(Collectors.toList());
    }

    public static DancerTo createDancerTo(Dancer dancer) {
        List<Dance> dances = new ArrayList<>();
        if (dancer.getDances() != null){
            dances = new ArrayList<>(dancer.getDances());
        }
        return new DancerTo(dancer.getId(), dancer.getImage(), dancer.getName(),
                dancer.getSurname(), dancer.getSex(),
                DateTimeUtils.fromLocalDateToDate(dancer.getBirthday()),
                dancer.getEntityInfo(), dancer.getRole(),
                dances, dancer.getDescription());
    }

    public static Dancer fromDancerTo(DancerTo dancerTo, Dancer dancer) {
        if (dancer.getId() == null) {
            Dancer transformDancer = new Dancer();
            transformDancer.setName(dancerTo.getName());
            transformDancer.setEntityInfo(dancerTo.getEntityInfo());
            transformDancer.setLoginPassword(dancerTo.getLoginPassword());
            return transformDancer;
        }
        dancer.setImage(dancerTo.getImage());
        dancer.setName(dancerTo.getName());
        dancer.setSurname(dancerTo.getSurname());
        dancer.setSex(dancerTo.getSex());
        dancer.setDescription(dancerTo.getDescription());
        dancer.setEntityInfo(dancerTo.getEntityInfo());
        dancer.setDances(new HashSet<>(dancerTo.getDances()));
        dancer.setRole(dancerTo.getRole());
        dancer.setBirthday(DateTimeUtils.fromDateToLocalDate(dancerTo.getBirthday()));
        return dancer;
    }
}
