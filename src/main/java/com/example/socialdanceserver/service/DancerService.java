package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.persistence.entity.DancerEntity;

import java.util.UUID;

public interface DancerService {

    PageDto<DancerDto> getPageDancers(String name, String lastName, String country, String city, int pageNumber, int size);

    DancerDto getById(UUID id);

    DancerDto save(DancerDto dancerDto);

    void deleteById(UUID id);

    DancerDto getDancerDtoFromEntity(DancerEntity dancerEntity);

}
