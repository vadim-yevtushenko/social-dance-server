package com.example.socialdanceserver.repository;

import com.example.socialdanceserver.TestDataService;
import com.example.socialdanceserver.persistence.repository.DancerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
