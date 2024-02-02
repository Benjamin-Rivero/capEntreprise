package fr.benjamin.cap_entreprise.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fr.benjamin.cap_entreprise.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Test
    public void contextLoads(){
        assertThat(reviewService).isNotNull();
    }

    @Test
    public void testReviewNumber() throws Exception {
        assertThat(reviewService.findAll().size()).isEqualTo(500);
    }

    @Test
    public void testFindReviewById(){
        assertThat(reviewService.findById(1L).getRating()).isEqualTo(20);
    }

    /*@Test
    public void testIdNotFound() throws Exception{
        assertThat(reviewService.findById(2547L)).isNotInstanceOf(Review.class);
    }*/

    @Test
    public void testRandomReviewsSize() {
        assertThat(reviewService.getRandomReviews().size()).isEqualTo(6);
    }

}