package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.dto.ReviewDto;
import com.example.socialdanceserver.api.dto.dto.SchoolDto;
import com.example.socialdanceserver.model.*;
import com.example.socialdanceserver.repository.SchoolRepository;
import com.example.socialdanceserver.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SchoolServiceImpl extends BaseService implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public List<SchoolDto> getAll() {
        return mapper.mapAsList(schoolRepository.findAll(Sort.by("name")), SchoolDto.class);
    }

    @Override
    public List<SchoolDto> getAllByName(String name) {
        return mapper.mapAsList(schoolRepository.findByNameContainsIgnoreCaseOrderByContactInfo_CityAsc(name), SchoolDto.class);
    }

    @Override
    public List<SchoolDto> getAllByCity(String city) {
        return mapper.mapAsList(schoolRepository.findByContactInfo_CityStartingWithIgnoreCaseOrderByNameAsc(city), SchoolDto.class);
    }

    @Override
    public SchoolDto getById(UUID id) {
        SchoolEntity schoolEntity = null;
        Optional<SchoolEntity> optionalSchool = schoolRepository.findById(id);
        if (optionalSchool.isPresent()){
            schoolEntity = optionalSchool.get();
        }
        return mapper.map(schoolEntity, SchoolDto.class);
    }

    @Override
    public SchoolDto save(SchoolDto school) {
        SchoolEntity schoolEntity = mapper.map(school, SchoolEntity.class);
        return mapper.map(schoolRepository.save(schoolEntity), SchoolDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        schoolRepository.deleteById(id);
    }

    @Override
    public List<ReviewDto> getReviewsBySchoolId(UUID id) {
        return null;
    }

}
