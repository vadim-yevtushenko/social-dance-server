package com.example.socialdanceserver.service.impl;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.api.dto.EventDto;
import com.example.socialdanceserver.api.dto.GeneralRatingDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.api.exceptions.badrequest.BadRequestException;
import com.example.socialdanceserver.persistence.dao.EventDao;
import com.example.socialdanceserver.persistence.entity.AbstractBaseEntity;
import com.example.socialdanceserver.persistence.entity.DancerEntity;
import com.example.socialdanceserver.persistence.entity.EventEntity;
import com.example.socialdanceserver.persistence.entity.SchoolEntity;
import com.example.socialdanceserver.persistence.repository.EventRepository;
import com.example.socialdanceserver.persistence.repository.SchoolRepository;
import com.example.socialdanceserver.service.*;
import com.example.socialdanceserver.service.model.PaginationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.mail.internet.InternetAddress;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl extends BaseService implements EventService {

    @Value("${frontend.url}")
    private String frontendUrl;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private DancerService dancerService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private ImageStorageService imageStorageService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private EventDao eventDao;

    @Override
    public PageDto<EventDto> getPageEvents(String name, String country, String city, int pageNumber, int size) {

        Map<String, String> mapPredicates = eventDao.getMapPredicates(name, country, city);
        int total = eventDao.getTotal(mapPredicates);

        PaginationRequest paginationRequest = buildPaginationRequest(List.of("dateFinishEvent"), mapPredicates, pageNumber, size, total);

        List<EventEntity> eventEntities = eventDao.load(paginationRequest);

        List<EventDto> eventDtos = mapper.mapAsList(eventEntities, EventDto.class).stream()
                .peek(eventDto -> {
                    GeneralRatingDto generalRatingDto = ratingService.createGeneralRatingForObject(eventDto.getId());
                    eventDto.setGeneralRating(generalRatingDto);
                })
                .collect(Collectors.toList());
        return new PageDto<>(total, eventDtos);
    }

    @Override
    public List<EventDto> getAllByOrganizerId(UUID id) {
        DancerDto dancerDto = dancerService.getById(id);
        if (dancerDto != null) {
            return mapper.mapAsList(eventRepository
                    .findDistinctByOrganizersInOrderByCreated(Set.of(mapper.map(dancerDto, DancerEntity.class))), EventDto.class);
        }
        return null;
    }

    @Override
    public List<EventDto> getAllBySchoolOrganizerId(UUID id) {
        return mapper.mapAsList(eventRepository.findDistinctBySchoolOrganizerIdOrderByCreated(id), EventDto.class);
    }

    @Override
    public EventDto getById(UUID id) {
        Optional<EventEntity> optionalEventEntity = eventRepository.findById(id);
        return optionalEventEntity.map(entity -> mapper.map(entity, EventDto.class)).orElse(null);
    }

    @Override
    public EventDto save(EventDto eventDto) {
        EventEntity eventEntity = mapper.map(eventDto, EventEntity.class);
        if (eventDto.getSchoolOrganizer() != null) {
            SchoolEntity schoolEntity = schoolRepository.getById(eventDto.getSchoolOrganizer().getId());
            eventEntity.setSchoolOrganizer(schoolEntity);
        }

        return mapper.map(eventRepository.save(eventEntity), EventDto.class);
    }

    @Override
    public EventDto saveWithCheck(EventDto eventDto, UUID eventOrganizerId) {
        if (eventDto.getOrganizers().isEmpty()){
            throw new BadRequestException("Failed to save! The school must have at least one organizer.");
        }
        if (eventDto.getId() != null){
            checkOrganizerForAccess(eventDto.getId(), eventOrganizerId);
        }

        return save(eventDto);
    }

    @Override
    public String uploadEventImage(UUID id, UUID eventOrganizerId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new BadRequestException("Failed to upload! File is empty.");
        }
        EventEntity eventEntity = eventRepository.findById(id).orElseGet(EventEntity::new);
        if (eventEntity.getImage() != null && !eventEntity.getImage().isBlank()){
            imageStorageService.deleteImage(eventEntity.getImage());
        }

        String url = imageStorageService.uploadImage(file);
        eventEntity.setImage(url);
        eventRepository.save(eventEntity);
        return url;
    }

    @Override
    public void deleteById(UUID id) {
        EventEntity eventEntity = eventRepository.findById(id).orElseGet(EventEntity::new);
        validateFound(eventEntity.getId(), EventEntity.class, id);
        reviewService.deleteReviewEntities(reviewService.getByObjectId(id));
        ratingService.deleteRatings(ratingService.getByObjectId(id));

        if (eventEntity.getImage() != null && !eventEntity.getImage().equals("")) {
            imageStorageService.deleteImage(eventEntity.getImage());
        }
        eventRepository.deleteById(id);
    }

    @Override
    public void deleteByIdWithCheck(UUID id, UUID organizerId) {
        checkOrganizerForAccess(id, organizerId);
        deleteById(id);
    }

    @Override
    public void deleteEventImage(UUID id, UUID organizerId) {
        checkOrganizerForAccess(id, organizerId);
        EventEntity eventEntity = eventRepository.findById(id).orElseGet(EventEntity::new);
        imageStorageService.deleteImage(eventEntity.getImage());
        eventEntity.setImage(null);
        eventRepository.save(eventEntity);
    }

    private void checkOrganizerForAccess(UUID id, UUID organizerId) {
        EventEntity eventEntity = eventRepository.findById(id).orElseGet(EventEntity::new);
        validateFound(eventEntity.getId(), EventEntity.class, id);
        List<UUID> uuids = eventEntity.getOrganizers().stream().map(AbstractBaseEntity::getId).collect(Collectors.toList());
        if (!uuids.contains(organizerId)) {
            throw new BadRequestException("Failed to save! You are not an organizer of this event.");
        }
    }

    @Override
    public void sendEmailsWhenEventCreated(EventDto event) {
        String city = event.getContactInfo().getCity();
        List<InternetAddress> internetAddresses = dancerService.getInternetAddressesByCity(city);
        String subject = "Created new event";
        String message = String.format("<br/><br/>Created new event %s in %s city.<br/><br/>" +
                "You can follow the link to view the new event:<br/>" +
                "%s/events/%s<br/><br/>", event.getName(), city, frontendUrl, event.getId());
        emailService.sendEmails(internetAddresses, subject, message);
    }

}
