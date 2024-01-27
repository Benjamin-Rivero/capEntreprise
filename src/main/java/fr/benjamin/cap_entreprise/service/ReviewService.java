package fr.benjamin.cap_entreprise.service;

import fr.benjamin.cap_entreprise.DTO.ReviewDTO;
import fr.benjamin.cap_entreprise.entity.Moderator;
import fr.benjamin.cap_entreprise.entity.Player;
import fr.benjamin.cap_entreprise.entity.Review;
import fr.benjamin.cap_entreprise.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Page<Review> findAllFiltered(String search1, String search2, Pageable page, List<String> params){

        List<Review> reviews = reviewRepository.findAllByGameNameContainingIgnoreCaseOrPlayerUsernameContainingIgnoreCase(search1,search2,Sort.by(getSortOrder(params)));
        int start = (int)page.getOffset();
        int end = Math.min((start+page.getPageSize()),reviews.size());
        List<Review> pageContent = reviews.subList(start,end);
        return new PageImpl<>(pageContent,page, reviews.size());
    }

    private List<Sort.Order> getSortOrder(List<String> params){
        List<Sort.Order> orders = new ArrayList<>();
        if(params!=null) {
            if (params.get(0).contains(",")) {
                params.forEach(param -> {
                    String[] ab = param.split(",");
                    if (ab[1].equals("asc")) {
                        orders.add(new Sort.Order(Sort.Direction.ASC, ab[0]));
                    }
                    if (ab[1].equals("desc")) {
                        orders.add(new Sort.Order(Sort.Direction.DESC, ab[0]));
                    }
                });
            } else {
                if (params.get(1).equals("asc")) {
                    orders.add(new Sort.Order(Sort.Direction.ASC, params.get(0)));
                }
                if (params.get(1).equals("desc")) {
                    orders.add(new Sort.Order(Sort.Direction.DESC, params.get(0)));
                }
            }
        }
        return orders;
    }
}