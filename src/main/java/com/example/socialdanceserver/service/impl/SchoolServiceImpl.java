package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.GeneralRatingDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.api.dto.SchoolDto;
import com.example.socialdanceserver.api.exceptions.badrequest.BadRequestException;
import com.example.socialdanceserver.persistence.dao.SchoolDao;
import com.example.socialdanceserver.persistence.entity.AbstractBaseEntity;
import com.example.socialdanceserver.persistence.entity.SchoolEntity;
import com.example.socialdanceserver.persistence.repository.SchoolRepository;
import com.example.socialdanceserver.service.*;
import com.example.socialdanceserver.service.model.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.internet.InternetAddress;
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
    private ImageStorageService imageStorageService;

    @Autowired
    private DancerService dancerService;

    @Autowired
    private EmailService emailService;

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
                    GeneralRatingDto generalRatingDto = ratingService.createGeneralRatingForObject(schoolDto.getId());
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

        if (schoolDto.getAdministrators().isEmpty()){
            throw new BadRequestException("Failed to save! The school must have at least one administrator.");
        }
        if (schoolDto.getId() != null){
            checkAdminForAccess(schoolDto.getId(), schoolAdminId);
        }

        return save(schoolDto);
    }

    @Override
    public String uploadSchoolImage(UUID id, UUID schoolAdminId, MultipartFile file) {
        checkAdminForAccess(id, schoolAdminId);
        if (file.isEmpty()) {
            throw new BadRequestException("Failed to upload! File is empty.");
        }
        SchoolEntity schoolEntity = schoolRepository.findById(id).orElseGet(SchoolEntity::new);
        if (schoolEntity.getImage() != null && !schoolEntity.getImage().isBlank()){
            imageStorageService.deleteImage(schoolEntity.getImage());
        }

        String url = imageStorageService.uploadImage(file);
        schoolEntity.setImage(url);
        schoolRepository.save(schoolEntity);
        return url;
    }

    @Override
    public void deleteById(UUID id) {
        SchoolEntity schoolEntity = schoolRepository.findById(id).orElseGet(SchoolEntity::new);
        validateFound(schoolEntity.getId(), SchoolEntity.class, id);
        reviewService.deleteReviewEntities(reviewService.getByObjectId(id));
        ratingService.deleteRatings(ratingService.getByObjectId(id));

        if (schoolEntity.getImage() != null && !schoolEntity.getImage().equals("")){
            imageStorageService.deleteImage(schoolEntity.getImage());
        }

        schoolRepository.deleteById(id);
    }

    @Override
    public void deleteByIdWithCheck(UUID id, UUID schoolAdminId) {
        checkAdminForAccess(id, schoolAdminId);
        deleteById(id);
    }

    @Override
    public void deleteSchoolImage(UUID id, UUID schoolAdminId) {
        checkAdminForAccess(id, schoolAdminId);
        SchoolEntity schoolEntity = schoolRepository.findById(id).orElseGet(SchoolEntity::new);
        imageStorageService.deleteImage(schoolEntity.getImage());
        schoolEntity.setImage(null);
        schoolRepository.save(schoolEntity);
    }

    private void checkAdminForAccess(UUID schoolId, UUID adminId) {
        SchoolEntity schoolEntity = schoolRepository.findById(schoolId).orElseGet(SchoolEntity::new);
        validateFound(schoolEntity.getId(), SchoolEntity.class, schoolId);
        List<UUID> uuids = schoolEntity.getAdministrators().stream().map(AbstractBaseEntity::getId).collect(Collectors.toList());
        if (!uuids.contains(adminId)) {
            throw new BadRequestException("Failed to save! You are not an administrator of this school.");
        }
    }

    @Override
    public void sendEmailsWhenSchoolCreated(SchoolDto school) {
        String city = school.getContactInfo().getCity();
        List<InternetAddress> internetAddresses = dancerService.getInternetAddressesByCity(city);
        String subject = "Created new school";
        String message = String.format("<br/><br/>Created new school %s in %s city.<br/><br/>" +
                "You can follow the link to view the new school:<br/>" +
                "http://localhost:3000/schools/%s<br/><br/>", school.getName(), city, school.getId());
        emailService.sendEmails(internetAddresses, subject, message);
    }

}
