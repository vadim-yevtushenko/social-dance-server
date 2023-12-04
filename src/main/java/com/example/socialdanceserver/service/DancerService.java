package com.example.socialdanceserver.service;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.persistence.entity.DancerEntity;
import javax.mail.internet.InternetAddress;
import java.util.List;
import java.util.UUID;

public interface DancerService {

    PageDto<DancerDto> getPageDancers(String name, String lastName, String country, String city, int pageNumber, int size);

    DancerDto getById(UUID id);

    DancerDto save(DancerDto dancerDto);

    void deleteById(UUID id, String password);

    DancerDto getDancerDtoFromEntity(DancerEntity dancerEntity);

    List<DancerEntity> getDancersByCity(String city);

    List<InternetAddress> getInternetAddressesByCity(String city);

}
