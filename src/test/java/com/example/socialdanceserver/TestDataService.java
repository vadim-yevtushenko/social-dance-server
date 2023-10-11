package com.example.socialdanceserver;

import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.api.dto.EventDto;
import com.example.socialdanceserver.api.dto.SchoolDto;
import com.example.socialdanceserver.persistence.entity.ContactInfoEntity;
import com.example.socialdanceserver.persistence.entity.enums.Level;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TestDataService{

    public List<DancerDto> createTestDancers() {
        DancerDto dancer1 = new DancerDto();
        dancer1.setId(UUID.randomUUID());
        dancer1.setName("Testname");
        dancer1.setLastName("Testlastname");
        dancer1.setLevel(Level.JUNIOR);
        dancer1.setGender("male");
        dancer1.setDescription("test description");

        DancerDto dancer2 = new DancerDto();
        dancer2.setId(UUID.randomUUID());
        dancer2.setName("Testname2");
        dancer2.setLastName("Testlastname2");
        dancer2.setLevel(Level.JUNIOR);
        dancer2.setGender("male");
        dancer2.setDescription("test2 description");

        DancerDto dancer3 = new DancerDto();
        dancer3.setId(UUID.randomUUID());
        dancer3.setName("Testname3");
        dancer3.setLastName("Testlastname3");
        dancer3.setLevel(Level.JUNIOR);
        dancer3.setGender("male");
        dancer3.setDescription("test3 description");

        return List.of(dancer1, dancer2, dancer3);
    }

    public DancerDto createTestDancer() {
        DancerDto dancer = new DancerDto();
        dancer.setId(UUID.fromString("f92dc1d8-6cc6-481b-92c3-3209889db3e5"));
        dancer.setName("Testname");
        dancer.setLastName("Testlastname");
        dancer.setLevel(Level.JUNIOR);
        dancer.setGender("male");
        dancer.setDescription("test description");

        return dancer;
    }

    public List<EventDto> createTestEvents() {

        EventDto eventDto1 = new EventDto();
        eventDto1.setId(UUID.randomUUID());
        eventDto1.setName("TestEvent1");

        EventDto eventDto2 = new EventDto();
        eventDto2.setId(UUID.randomUUID());
        eventDto2.setName("TestEvent2");

        EventDto eventDto3 = new EventDto();
        eventDto3.setId(UUID.randomUUID());
        eventDto3.setName("TestEvent3");

        return List.of(eventDto1, eventDto2, eventDto3);
    }

    public EventDto createTestEvent() {
        EventDto eventDto = new EventDto();
        eventDto.setId(UUID.fromString("f92dc1d8-6cc6-481b-92c3-3209889db3e5"));
        eventDto.setName("TestEvent");

        return eventDto;
    }

    public List<SchoolDto> createTestSchools() {

        SchoolDto schoolDto1 = new SchoolDto();
        schoolDto1.setId(UUID.randomUUID());
        schoolDto1.setName("TestSchool1");

        SchoolDto schoolDto2 = new SchoolDto();
        schoolDto2.setId(UUID.randomUUID());
        schoolDto2.setName("TestSchool2");

        SchoolDto schoolDto3 = new SchoolDto();
        schoolDto3.setId(UUID.randomUUID());
        schoolDto3.setName("TestSchool3");

        return List.of(schoolDto1, schoolDto2, schoolDto3);
    }

    public SchoolDto createTestSchool() {
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setId(UUID.fromString("f92dc1d8-6cc6-481b-92c3-3209889db3e5"));
        schoolDto.setName("TestSchool");

        return schoolDto;
    }
    private ContactInfoEntity createTestContactInfo() {
        ContactInfoEntity contactInfo = new ContactInfoEntity();
        contactInfo.setCountry("Ukraine");
        contactInfo.setCity("Kiev");
        contactInfo.setEmail("test@mail.com");
        contactInfo.setPhoneNumber("5555555");

        return contactInfo;
    }
}
