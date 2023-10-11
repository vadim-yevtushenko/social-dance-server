package com.example.socialdanceserver.api;

import com.example.socialdanceserver.BaseTest;
import com.example.socialdanceserver.TestDataService;
import com.example.socialdanceserver.api.controller.DancerRestController;
import com.example.socialdanceserver.api.dto.DancerDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.service.DancerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DancerControllerTest extends BaseTest {

    @Autowired
    private TestDataService testDataService;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();

    @InjectMocks
    private DancerRestController dancerRestController;

    @Mock
    private DancerService dancerService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
//        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(dancerRestController).build();
    }

    @Test
    public void getPageDancersTest() throws Exception {
        List<DancerDto> dancers= testDataService.createTestDancers();
        PageDto<DancerDto> dancersDto = new PageDto<>(dancers.size(), dancers);

        Mockito.when(dancerService.getPageDancers(null, null, null, null, 1, 10))
                .thenReturn(dancersDto);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/dancers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(3)))
                .andExpect(jsonPath("$.results[2].name", is("Testname3")));

    }

    @Test
    public void getByIdTest() throws Exception {
        DancerDto dancer= testDataService.createTestDancer();

        Mockito.when(dancerService.getById(dancer.getId()))
                .thenReturn(dancer);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/dancers/f92dc1d8-6cc6-481b-92c3-3209889db3e5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Testname")));

    }

    @Test
    public void createDancer() throws Exception {
        DancerDto dancer= testDataService.createTestDancer();

        Mockito.when(dancerService.save(any(DancerDto.class)))
                .thenReturn(dancer);

        String content = objectWriter.writeValueAsString(dancer);

        MockHttpServletRequestBuilder mockPostRequest = MockMvcRequestBuilders.post("/dancers")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockPostRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("Testname")));
    }
}
