package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.api.dto.PageDto;
import java.util.UUID;

public interface DancerService {

    PageDto<DancerDto> getPageDancers(String name, String lastName, String city, int pageNumber, int size);

    DancerDto getById(UUID id);

    DancerDto save(DancerDto dancerDto);

    void deleteById(UUID id);

}
