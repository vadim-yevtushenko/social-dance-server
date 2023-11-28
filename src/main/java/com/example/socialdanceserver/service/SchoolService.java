package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.api.dto.SchoolDto;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

public interface SchoolService {

    PageDto<SchoolDto> getPageSchools(String name, String country, String city, int pageNumber, int size);

    SchoolDto getById(UUID id);

    SchoolDto save(SchoolDto school);

    SchoolDto saveWithCheck(SchoolDto school, UUID schoolAdminId);

    String uploadSchoolImage(UUID id, UUID schoolAdminId, MultipartFile file);

    void deleteById(UUID id);

    void deleteByIdWithCheck(UUID id, UUID schoolAdminId);

    void deleteSchoolImage(UUID id, UUID schoolAdminId);

    void sendEmailsWhenSchoolCreated(SchoolDto school);

}
