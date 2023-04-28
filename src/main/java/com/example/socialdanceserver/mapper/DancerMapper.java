package com.example.socialdanceserver.mapper;

import com.example.socialdanceserver.dto.DancerDto;
import com.example.socialdanceserver.model.DancerEntity;
import com.example.socialdanceserver.model.enums.Dance;
import com.example.socialdanceserver.util.DateTimeUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class DancerMapper {

    public static List<DancerDto> mapDancerTos(List<DancerEntity> dancerEntityList) {
        return dancerEntityList.stream().map(DancerMapper::mapDancerEntity).
                collect(Collectors.toList());
    }

    public static DancerDto mapDancerEntity(DancerEntity dancerEntity) {
        List<Dance> dances = new ArrayList<>();
        if (dancerEntity.getDances() != null){
            dances = new ArrayList<>(dancerEntity.getDances());
        }
        return new DancerDto(dancerEntity.getId(), dancerEntity.getImage(), dancerEntity.getName(),
                dancerEntity.getSurname(), dancerEntity.getGender(),
                DateTimeUtils.fromLocalDateToDate(dancerEntity.getBirthday()),
                dancerEntity.getEntityInfo(), dancerEntity.getRole(),
                dances, dancerEntity.getDescription());
    }

    public static DancerEntity populateDancerEntity(DancerDto dancerDto, DancerEntity dancerEntity) {
        if (dancerEntity.getId() == null) {
            DancerEntity transformDancerEntity = new DancerEntity();
            transformDancerEntity.setName(dancerDto.getName());
            transformDancerEntity.setEntityInfo(dancerDto.getEntityInfo());
            transformDancerEntity.setLoginPassword(dancerDto.getLoginPassword());
            return transformDancerEntity;
        }
        dancerEntity.setImage(dancerDto.getImage());
        dancerEntity.setName(dancerDto.getName());
        dancerEntity.setSurname(dancerDto.getSurname());
        dancerEntity.setGender(dancerDto.getSex());
        dancerEntity.setDescription(dancerDto.getDescription());
        dancerEntity.setEntityInfo(dancerDto.getEntityInfo());
        dancerEntity.setDances(new HashSet<>(dancerDto.getDances()));
        dancerEntity.setRole(dancerDto.getRole());
        dancerEntity.setBirthday(DateTimeUtils.fromDateToLocalDate(dancerDto.getBirthday()));
        return dancerEntity;
    }
}
