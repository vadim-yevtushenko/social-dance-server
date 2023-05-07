package com.example.socialdanceserver;

import com.example.socialdanceserver.model.ContactInfoEntity;
import com.example.socialdanceserver.model.DanceEntity;
import com.example.socialdanceserver.model.DancerEntity;
import com.example.socialdanceserver.model.enums.Role;
import com.example.socialdanceserver.repository.DancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TestDataService {

    @Autowired
    private DancerRepository dancerRepository;

    public DancerEntity createTestDancer() {
        DancerEntity dancer = new DancerEntity();
        dancer.setName("Testname");
        dancer.setLastName("Testlastname");
        dancer.setContactInfo(createTestContactInfo());
        dancer.setRole(Role.DANCER);
        dancer.setGender("male");
        dancer.setBirthday(LocalDate.now());
        dancer.setDances(List.of(new DanceEntity(1, "Salsa")));
        dancer.setDescription("test description");

        return dancerRepository.save(dancer);
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
