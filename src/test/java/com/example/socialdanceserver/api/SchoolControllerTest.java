package com.example.socialdanceserver.api;

import com.example.socialdanceserver.BaseTest;
import com.example.socialdanceserver.TestDataService;
import com.example.socialdanceserver.api.controller.SchoolRestController;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.api.dto.SchoolDto;
import com.example.socialdanceserver.service.SchoolService;
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

public class SchoolControllerTest extends BaseTest {

    @Autowired
    private TestDataService testDataService;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();

    @InjectMocks
    private SchoolRestController schoolRestController;

    @Mock
    private SchoolService schoolService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(schoolRestController).build();
    }

    @Test
    public void getPageSchoolsTest() throws Exception {
        List<SchoolDto> schools= testDataService.createTestSchools();
        PageDto<SchoolDto> schoolPageDto = new PageDto<>(schools.size(), schools);

        Mockito.when(schoolService.getPageSchools(null, null, null, 1, 10))
                .thenReturn(schoolPageDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/schools")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(3)))
                .andExpect(jsonPath("$.results[2].name", is("TestSchool3")));

    }

    @Test
    public void getByIdTest() throws Exception {
        SchoolDto schoolDto = testDataService.createTestSchool();

        Mockito.when(schoolService.getById(schoolDto.getId()))
                .thenReturn(schoolDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/schools/f92dc1d8-6cc6-481b-92c3-3209889db3e5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("TestSchool")));

    }

    @Test
    public void createSchool() throws Exception {
        SchoolDto schoolDto= testDataService.createTestSchool();

        Mockito.when(schoolService.save(any(SchoolDto.class)))
                .thenReturn(schoolDto);

        String content = objectWriter.writeValueAsString(schoolDto);

        MockHttpServletRequestBuilder mockPostRequest = MockMvcRequestBuilders.post("/schools")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockPostRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("TestSchool")));
    }
}
