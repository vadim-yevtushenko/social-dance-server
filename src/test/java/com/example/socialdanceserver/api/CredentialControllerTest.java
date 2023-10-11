package com.example.socialdanceserver.api;

import com.example.socialdanceserver.BaseTest;
import com.example.socialdanceserver.api.controller.CredentialRestController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CredentialControllerTest extends BaseTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CredentialRestController credentialController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(credentialController).build();
    }

    @Test
    public void loginTest() throws Exception {
//        mockMvc.perform(get("/"))
//                .andDo(print())
//                .andExpect(status().isOk());
    }
}
