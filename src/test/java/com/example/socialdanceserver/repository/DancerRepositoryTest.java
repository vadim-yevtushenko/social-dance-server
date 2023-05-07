package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.TestDataService;
import com.example.socialdanceserver.model.DancerEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
public class DancerRepositoryTest{

//    @Autowired
//    protected MockMvc mvc;

    @Autowired
    private TestDataService testDataService;

    @Autowired
    private DancerRepository dancerRepository;

    @Test
    public void getById() {

    }

}
