package fr.benjamin.cap_entreprise.repository;

import fr.benjamin.cap_entreprise.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Query("select g from Game g join Review r on g.id = r.game.id group by g.id order by count(r.game.id) desc limit 20")
    List<Game> findTop20ByOrderByCountReview();

}