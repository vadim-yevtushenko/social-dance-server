package com.example.socialdanceserver.util;

import com.example.socialdanceserver.dto.DancerTo;
import com.example.socialdanceserver.model.Dancer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DancerUtils {

    public static List<DancerTo> getDancerTos(List<Dancer> dancerList){
        return dancerList.stream().map(DancerUtils::createDancerTo).
                collect(Collectors.toList());
    }

    public static DancerTo createDancerTo(Dancer dancer){
        return new DancerTo(dancer.getId(), dancer.getName(),
                dancer.getSurname(), dancer.getSex(),
                DateTimeUtils.fromLocalDateTimeToDate(dancer.getBirthday()),
                dancer.getEntityInfo(), dancer.getRole(), new ArrayList<>(dancer.getDances()));
    }
}
