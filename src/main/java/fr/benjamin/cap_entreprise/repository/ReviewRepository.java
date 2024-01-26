package fr.benjamin.cap_entreprise.repository;

import fr.benjamin.cap_entreprise.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByModeratorNotNull();

}