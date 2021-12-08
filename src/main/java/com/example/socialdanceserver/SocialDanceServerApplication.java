package com.example.socialdanceserver;

import com.example.socialdanceserver.model.*;
import com.example.socialdanceserver.model.enums.Dance;
import com.example.socialdanceserver.model.enums.Role;
import com.example.socialdanceserver.repository.AbstractBaseEntityRepository;
import com.example.socialdanceserver.repository.DancerRepository;
import com.example.socialdanceserver.repository.EventRepository;
import com.example.socialdanceserver.repository.SchoolRepository;
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

        EntityInfo entityInfo = new EntityInfo();
        EntityInfo entityInfo1 = new EntityInfo();
        EntityInfo entityInfo2 = new EntityInfo();
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
        dancer.setRatings(new HashSet<>());
        dancer.getRatings().add(new Rating(dancer, 10));

        School school =
                new School("latin dance", "salsa, bachata", entityInfo1);
        school.setReviews(new ArrayList<>());
        school.getReviews().add(new Review(1, school, "good review", LocalDateTime.now()));

        Event event =
                new Event("party", "open air", entityInfo2,
                        LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());

        event.setDances(new HashSet<>());
        event.getDances().add(Dance.SALSA);
        event.getDances().add(Dance.BACHATA);

        dancerRepo.save(dancer);
        schoolRepo.save(school);
        eventRepo.save(event);

//        eventRepo.deleteAll();
        Dancer dancer1 = null;
        Optional<AbstractBaseEntity> dancer123 = dancerRepo.findById(1);
        if (dancer123.isPresent()) {
            dancer1 = (Dancer) dancer123.get();
            System.out.println(dancer1);
        }

        dancer1.setRole(Role.TEACHER);
        dancerRepo.save(dancer1);



        Optional<AbstractBaseEntity> dancer12 = dancerRepo.findById(1);
        if (dancer12.isPresent()) {
            Dancer dan = (Dancer) dancer12.get();
            System.out.println(dan);
        }

        Optional<AbstractBaseEntity> event123 = eventRepo.findById(3);
        if (event123.isPresent()) {
            AbstractBaseEntity d =  event123.get();
            System.out.println(d);
        }

        Optional<AbstractBaseEntity> school123 = eventRepo.findById(2);
        if (school123.isPresent()) {
            AbstractBaseEntity d =  school123.get();
            System.out.println(d);
        }
    }
}
