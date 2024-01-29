package fr.benjamin.cap_entreprise.repository;

import fr.benjamin.cap_entreprise.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("Select r From Review r Join Game g on r.game = g join User u on r.player = u where (r.moderator is not NULL or u.username like ?3) AND (g.name like %?1% or u.username like %?2%)")
    Page<Review> findAllByGameNameContainingIgnoreCaseOrPlayerUsernameContainingIgnoreCase(String search1, String search2, String username, Pageable pageable);

    @Query("Select r From Review r Join Game g on r.game = g join User u on r.player = u where g.name like %?1% or u.username like %?2%")
    Page<Review> findAllForModerator(String search1, String search2, String username, Pageable pageable);

    @Query("Select r From Review r Join Game g on r.game = g join User u on r.player = u where r.moderator is not NULL AND (g.name like %?1% or u.username like %?2%)")
    Page<Review> findAllByModeratorNotNullAndGameNameContainingIgnoreCaseOrPlayerUsernameContainingIgnoreCase(String search1, String search2, Pageable pageable);

    @Query("Select r From Review r Join Game g on r.game = g join User u on r.player = u where r.moderator is NULL AND (g.name like %?1% or u.username like %?2%)")
    Page<Review> findAllByModeratorNullAndGameNameContainingIgnoreCaseOrPlayerUsernameContainingIgnoreCase(String search1, String search2, Pageable pageable);

}