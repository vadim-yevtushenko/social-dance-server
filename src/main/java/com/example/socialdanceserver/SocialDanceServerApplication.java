package com.example.socialdanceserver;

import com.example.socialdanceserver.model.*;
import com.example.socialdanceserver.model.enums.Dance;
import com.example.socialdanceserver.model.enums.Role;
import com.example.socialdanceserver.repository.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;

@SpringBootApplication
public class SocialDanceServerApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(SocialDanceServerApplication.class, args);

        AbstractBaseEntityRepository dancerRepo =
                context.getBean(DancerRepository.class);
        AbstractBaseEntityRepository schoolRepo =
                context.getBean(SchoolRepository.class);
        AbstractBaseEntityRepository eventRepo =
                context.getBean(EventRepository.class);
        LoginPasswordRepository logRepo =
                context.getBean(LoginPasswordRepository.class);

        EntityInfo entityInfo1 = new EntityInfo();
        EntityInfo entityInfo2 = new EntityInfo();
        EntityInfo entityInfo = new EntityInfo();
        entityInfo.setCountry("Ukraine");
        entityInfo.setCity("Zp");
        entityInfo.setEmail("dance@gmail.com");


        entityInfo1.setCountry("Ukraine");
        entityInfo1.setCity("Kiev");
        entityInfo1.setEmail("kiev.dance@gmail.com");

        entityInfo2.setCountry("Ukraine");
        entityInfo2.setCity("Dp");
        entityInfo2.setEmail("dp.party@gmail.com");
        AbstractBaseEntity dancer =
                new Dancer("Ivan", "salsa", entityInfo, "Ivanov",
                        "male", null, Role.DANCER);

//        dancerRepo.save(dancer);

        Optional<AbstractBaseEntity> dancer12 = dancerRepo.findById(1);
        if (dancer12.isPresent()) {
            dancer = dancer12.get();
            System.out.println(dancer);
        }

        School school =
                new School("latin dance", "salsa, bachata", entityInfo1, dancer.getId());

        school.setDances(new HashSet<>());
        school.getDances().add(Dance.SALSA);
        school.getDances().add(Dance.BACHATA);

//        schoolRepo.save(school);

        Optional<AbstractBaseEntity> school123 = eventRepo.findById(2);
        if (school123.isPresent()) {
            school = (School) school123.get();
            System.out.println(school);
        }

        Event event =
                new Event("party", "open air", entityInfo2, school.getId(),
                        LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());

        event.setDances(new HashSet<>());
        event.getDances().add(Dance.SALSA);
        event.getDances().add(Dance.BACHATA);

//        eventRepo.save(event);

        Optional<AbstractBaseEntity> event123 = eventRepo.findById(3);
        if (event123.isPresent()) {
            event = (Event) event123.get();
            System.out.println(event);
        }

        school.getRatings().add(new Rating(dancer, dancer.getId(), 7));
        school.getRatings().add(new Rating(event, dancer.getId(), 10));
        school.getRatings().add(new Rating(school, dancer.getId(), 8));
        school.getReviews().add(new Review(dancer.getId(), school, "good school", LocalDateTime.now()));

        System.out.println(((EventRepository)eventRepo).findAllByOwnerId(14));
        System.out.println(((SchoolRepository)schoolRepo).findAllByOwnerId(14));

        ((Dancer)dancer).setLoginPassword(new LoginPassword("a@b.c", "asd"));

//        dancerRepo.save(dancer);
//        schoolRepo.save(school);
//        eventRepo.save(event);
    }
}
