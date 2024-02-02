package fr.benjamin.cap_entreprise.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class HomeControllerTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRedirectFromHome() throws Exception{
        mockMvc.perform(get("/").with(anonymous()))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void testAccessHome() throws Exception {
        mockMvc.perform(get("/").with(user("lancelyde"))).
                andExpect(status().isOk());
    }

    


}