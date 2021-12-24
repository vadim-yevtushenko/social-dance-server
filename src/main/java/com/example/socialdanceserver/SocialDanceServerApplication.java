package com.example.socialdanceserver;

import com.example.socialdanceserver.dto.RatingTo;
import com.example.socialdanceserver.model.*;
import com.example.socialdanceserver.model.enums.Dance;
import com.example.socialdanceserver.model.enums.Role;
import com.example.socialdanceserver.repository.*;
import com.example.socialdanceserver.service.SchoolService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
        SchoolService schoolService =
                context.getBean(SchoolService.class);

    }
}
