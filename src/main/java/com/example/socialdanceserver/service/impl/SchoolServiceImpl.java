package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.api.dto.ReviewDto;
import com.example.socialdanceserver.api.dto.SchoolDto;
import com.example.socialdanceserver.persistence.dao.SchoolDao;
import com.example.socialdanceserver.persistence.repository.SchoolRepository;
import com.example.socialdanceserver.persistence.entity.SchoolEntity;
import com.example.socialdanceserver.service.SchoolService;
import com.example.socialdanceserver.service.model.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SchoolServiceImpl extends BaseService implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public PageDto<SchoolDto> getPageSchools(String name, String city, int pageNumber, int size) {

        Map<String, String> mapPredicates = schoolDao.getMapPredicates(name, city);
        int total = schoolDao.getTotal(mapPredicates);

        PaginationRequest paginationRequest = buildPaginationRequest(List.of("name"), mapPredicates, pageNumber, size, total);

        List<SchoolEntity> schoolEntities = schoolDao.load(paginationRequest);

        return new PageDto<>(total, mapper.mapAsList(schoolEntities, SchoolDto.class));
    }

    @Override
    public SchoolDto getById(UUID id) {
        Optional<SchoolEntity> schoolEntity = schoolRepository.findById(id);
        return schoolEntity.map(entity -> mapper.map(entity, SchoolDto.class)).orElse(null);
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
