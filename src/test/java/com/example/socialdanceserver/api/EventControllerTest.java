package com.example.socialdanceserver.api;

import com.example.socialdanceserver.BaseTest;
import com.example.socialdanceserver.TestDataService;
import com.example.socialdanceserver.api.controller.EventRestController;
import com.example.socialdanceserver.api.dto.EventDto;
import com.example.socialdanceserver.api.dto.PageDto;
import com.example.socialdanceserver.service.EventService;
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

public class EventControllerTest extends BaseTest {

    @Autowired
    private TestDataService testDataService;

    private ObjectMapper objectMapper = new ObjectMapper();
    private ObjectWriter objectWriter = objectMapper.writer();

    @InjectMocks
    private EventRestController eventRestController;

    @Mock
    private EventService eventService;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(eventRestController).build();
    }

    @Test
    public void getPageEventsTest() throws Exception {
        List<EventDto> events= testDataService.createTestEvents();
        PageDto<EventDto> eventPageDto = new PageDto<>(events.size(), events);

        Mockito.when(eventService.getPageEvents(null, null, null, 1, 10))
                .thenReturn(eventPageDto);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/events")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.total", is(3)))
                .andExpect(jsonPath("$.results[2].name", is("TestEvent3")));

    }

    @Test
    public void getByIdTest() throws Exception {
        EventDto eventDto = testDataService.createTestEvent();

        Mockito.when(eventService.getById(eventDto.getId()))
                .thenReturn(eventDto);
//        Mockito.doNothing().when(this.eventRestController).validateFound(eventDto, EventDto.class, eventDto.getId());

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/events/f92dc1d8-6cc6-481b-92c3-3209889db3e5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("TestEvent")));

    }

    @Test
    public void createEvent() throws Exception {
        EventDto eventDto= testDataService.createTestEvent();

        Mockito.when(eventService.save(any(EventDto.class)))
                .thenReturn(eventDto);

        String content = objectWriter.writeValueAsString(eventDto);

        MockHttpServletRequestBuilder mockPostRequest = MockMvcRequestBuilders.post("/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockPostRequest)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is("TestEvent")));
    }
}
