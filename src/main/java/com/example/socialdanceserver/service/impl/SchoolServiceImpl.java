package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.GeneralRatingDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.api.dto.SchoolDto;
import com.example.socialdanceserver.api.exceptions.badrequest.BadRequestException;
import com.example.socialdanceserver.persistence.dao.SchoolDao;
import com.example.socialdanceserver.persistence.entity.AbstractBaseEntity;
import com.example.socialdanceserver.persistence.entity.SchoolEntity;
import com.example.socialdanceserver.persistence.repository.SchoolRepository;
import com.example.socialdanceserver.service.RatingService;
import com.example.socialdanceserver.service.ReviewService;
import com.example.socialdanceserver.service.SchoolService;
import com.example.socialdanceserver.service.model.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl extends BaseService implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private SchoolDao schoolDao;

    @Override
    public PageDto<SchoolDto> getPageSchools(String name, String country, String city, int pageNumber, int size) {

        Map<String, String> mapPredicates = schoolDao.getMapPredicates(name, country, city);
        int total = schoolDao.getTotal(mapPredicates);

        PaginationRequest paginationRequest = buildPaginationRequest(List.of("image"), mapPredicates, pageNumber, size, total);

        List<SchoolEntity> schoolEntities = schoolDao.load(paginationRequest);

        List<SchoolDto> schoolDtos = mapper.mapAsList(schoolEntities, SchoolDto.class).stream()
                .peek(schoolDto -> {
                    GeneralRatingDto generalRatingDto = ratingService.createGeneralRatingForSchool(schoolDto.getId());
                    schoolDto.setGeneralRating(generalRatingDto);
                })
                .collect(Collectors.toList());

        return new PageDto<>(total, schoolDtos);
    }

    @Override
    public SchoolDto getById(UUID id) {
        Optional<SchoolEntity> schoolEntity = schoolRepository.findById(id);
        return schoolEntity.map(entity -> mapper.map(entity, SchoolDto.class)).orElse(null);
    }

    @Override
    public SchoolDto save(SchoolDto schoolDto) {

        SchoolEntity schoolEntity = mapper.map(schoolDto, SchoolEntity.class);
        return mapper.map(schoolRepository.save(schoolEntity), SchoolDto.class);
    }

    @Override
    public SchoolDto saveWithCheck(SchoolDto schoolDto, UUID schoolAdminId) {

        checkSchoolBeforeSaving(schoolDto, schoolAdminId);

        SchoolEntity schoolEntity = mapper.map(schoolDto, SchoolEntity.class);
        return mapper.map(schoolRepository.save(schoolEntity), SchoolDto.class);
    }

    @Override
    public void deleteById(UUID id) {
        reviewService.deleteReviewEntities(reviewService.getBySchoolId(id));
        ratingService.deleteRatings(ratingService.getBySchoolId(id));
        schoolRepository.deleteById(id);
    }

    private void checkSchoolBeforeSaving(SchoolDto schoolDto, UUID adminId) {
        if (schoolDto.getAdministrators().isEmpty()){
            throw new BadRequestException("Failed to save! The school must have at least one administrator.");
        }
        if (schoolDto.getId() != null){
            SchoolEntity schoolEntity = schoolRepository.getById(schoolDto.getId());
            List<UUID> uuids = schoolEntity.getAdministrators().stream().map(AbstractBaseEntity::getId).collect(Collectors.toList());
            if (!uuids.contains(adminId)) {
                throw new BadRequestException("Failed to save! You are not an administrator of this school.");
            }
        }

    }

}
