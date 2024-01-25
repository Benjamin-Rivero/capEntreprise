package fr.benjamin.cap_entreprise.service;

import fr.benjamin.cap_entreprise.DTO.ReviewDTO;
import fr.benjamin.cap_entreprise.entity.Moderator;
import fr.benjamin.cap_entreprise.entity.Player;
import fr.benjamin.cap_entreprise.entity.Review;
import fr.benjamin.cap_entreprise.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final GameService gameService;
    private final UserService userService;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review persist(ReviewDTO dto) {
        Review review = new Review();
        review.setGame(gameService.findById(dto.getGameId()));
        review.setDescription(dto.getDescription());
        review.setRating(dto.getRating());
        review.setPlayer((Player)userService.findById(dto.getPlayerId()));
        return reviewRepository.saveAndFlush(review);
    }

    public Review findById(Long id) {
        return this.reviewRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Review validate(Long id,Long moderatorId){
        Review review = findById(id);
        review.setModerator((Moderator)userService.findById(moderatorId));
        return this.reviewRepository.saveAndFlush(review);
    }

    public void refuse(Long id){
        this.reviewRepository.deleteById(id);
    }

    public Page<Review> findAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }
}