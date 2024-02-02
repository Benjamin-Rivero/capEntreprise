package fr.benjamin.cap_entreprise.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import fr.benjamin.cap_entreprise.entity.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

@SpringBootTest
public class GameServiceTest {


    @Autowired
    private GameService gameService;

    @Test
    public void contextLoads(){
        assertThat(gameService).isNotNull();
    }

    @Test
    public void testGameNumber(){
        assertThat(gameService.findAll().size()).isEqualTo(21);
    }

    @Test
    public void testRandomGameNumber(){
        assertThat(gameService.getRandomGames().size()).isEqualTo(4);
    }

    @Test
    public void testFindAllSortedFirstElement(){
        assertThat(gameService.findAllSorted(Sort.by("name").ascending()).get(0).getName()).isEqualTo("Anno 1800");
    }

}