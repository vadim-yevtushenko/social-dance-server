package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.api.dto.SchoolDto;
import java.util.UUID;

public interface SchoolService {

    PageDto<SchoolDto> getPageSchools(String name, String country, String city, int pageNumber, int size);

    SchoolDto getById(UUID id);

    SchoolDto save(SchoolDto school);

    void deleteById(UUID id);

}
