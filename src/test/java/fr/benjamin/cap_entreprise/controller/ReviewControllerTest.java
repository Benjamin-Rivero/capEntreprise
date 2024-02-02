package fr.benjamin.cap_entreprise.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import fr.benjamin.cap_entreprise.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    /*@Test
    public void testPageNumberInfos() throws Exception {
        mockMvc.perform(get("/?sort=moderator,asc")
                        .with(user("lancelyde").roles("MODERATOR"))
                        .contentType(MediaType.TEXT_HTML)
                )
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute(
                                "pageReviews",
                                reviewService.getPageReviewByNickname(
                                        "lancelyde",
                                        PageRequest.of(
                                                1,
                                                6,
                                                Sort.by("moderator"
                                                ).ascending())
                                )
                        )
                );
    }

    @Test
    public void testAccessReviewIndex() throws Exception{
        mockMvc.perform(get("/avis").with(user("evankeel")))
                .andExpect(status().isOk());
    }*/


}