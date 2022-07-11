package com.example.socialdanceserver.mapper;

import com.example.socialdanceserver.dto.DancerTo;
import com.example.socialdanceserver.model.DancerEntity;
import com.example.socialdanceserver.model.enums.Dance;
import com.example.socialdanceserver.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class DancerMapper {

    public static List<DancerTo> mapDancerTos(List<DancerEntity> dancerEntityList) {
        return dancerEntityList.stream().map(DancerMapper::mapDancerEntity).
                collect(Collectors.toList());
    }

    public static DancerTo mapDancerEntity(DancerEntity dancerEntity) {
        List<Dance> dances = new ArrayList<>();
        if (dancerEntity.getDances() != null){
            dances = new ArrayList<>(dancerEntity.getDances());
        }
        return new DancerTo(dancerEntity.getId(), dancerEntity.getImage(), dancerEntity.getName(),
                dancerEntity.getSurname(), dancerEntity.getSex(),
                DateTimeUtils.fromLocalDateToDate(dancerEntity.getBirthday()),
                dancerEntity.getEntityInfo(), dancerEntity.getRole(),
                dances, dancerEntity.getDescription());
    }

    public static DancerEntity populateDancerEntity(DancerTo dancerTo, DancerEntity dancerEntity) {
        if (dancerEntity.getId() == null) {
            DancerEntity transformDancerEntity = new DancerEntity();
            transformDancerEntity.setName(dancerTo.getName());
            transformDancerEntity.setEntityInfo(dancerTo.getEntityInfo());
            transformDancerEntity.setLoginPassword(dancerTo.getLoginPassword());
            return transformDancerEntity;
        }
        dancerEntity.setImage(dancerTo.getImage());
        dancerEntity.setName(dancerTo.getName());
        dancerEntity.setSurname(dancerTo.getSurname());
        dancerEntity.setSex(dancerTo.getSex());
        dancerEntity.setDescription(dancerTo.getDescription());
        dancerEntity.setEntityInfo(dancerTo.getEntityInfo());
        dancerEntity.setDances(new HashSet<>(dancerTo.getDances()));
        dancerEntity.setRole(dancerTo.getRole());
        dancerEntity.setBirthday(DateTimeUtils.fromDateToLocalDate(dancerTo.getBirthday()));
        return dancerEntity;
    }
}
