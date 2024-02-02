package fr.benjamin.cap_entreprise.service;

import fr.benjamin.cap_entreprise.DTO.ReviewDTO;
import fr.benjamin.cap_entreprise.entity.*;
import fr.benjamin.cap_entreprise.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public Page<Review> findAllFiltered(String search1, String search2,String username, Pageable page,String moderation){
        if(search1==null){
            search1="";
            search2="";
        }
            if (moderation == null) {
                if(userService.findByUsername(username).isAdmin()){
                    return reviewRepository.findAllForModerator(search1, search2,username, page);
                }
                return reviewRepository.findAllByGameNameContainingIgnoreCaseOrPlayerUsernameContainingIgnoreCase
                        (search1, search2, username, page);
            }
            if (moderation.equals("1")) {
                return reviewRepository.findAllByModeratorNullAndGameNameContainingIgnoreCaseOrPlayerUsernameContainingIgnoreCase
                        (search1, search2, page);
            }
            if (moderation.equals("2")) {
                return reviewRepository.findAllByModeratorNotNullAndGameNameContainingIgnoreCaseOrPlayerUsernameContainingIgnoreCase
                        (search1, search2, page);
            }

        return null;
    }

    public List<Review> getRandomReviews() {
        List<Review> reviews = reviewRepository.findAll();
        List<Review> randoms = new ArrayList<>();
        while(randoms.size()<6){
            int selected = (int)Math.floor(Math.random()*(reviews.size()));
            if(reviews.get(selected).getModerator() == null){
                continue;
            }
            randoms.add(reviews.get(selected));
            reviews.remove(selected);
        }
        return randoms;
    }

    public Page<Review> findByGameAndModeratorNotNull(Game game, Pageable pageable) {
        return reviewRepository.findByGameAndModeratorNotNull(game,pageable);
    }

    public Review createReview(ReviewDTO reviewDTO, Game game, String name) {
        Review review = new Review();
        review.setGame(game);
        review.setPlayer((Player) userService.findByUsername(name));
        review.setDescription(reviewDTO.getDescription());
        review.setRating(reviewDTO.getRating());
        review.setCreatedAt(LocalDateTime.now());
        return reviewRepository.saveAndFlush(review);
    }

    /*private List<Sort.Order> getSortOrder(List<String> params){
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
    }*/

    public Page<Review> findByUserNickname(String nickname, Pageable pageable) {
        return reviewRepository.findByModeratorIsNotNullOrPlayerUsername(nickname, pageable);
    }

    public Page<Review> getPageReviewByNickname(String nickname, Pageable pageable) {
        User user = userService.findByUsername(nickname);
        Page<Review> pageReviews = findByUserNickname(nickname, pageable);
        if (user.isModerator()) {
            Sort.Order order = pageable.getSort().getOrderFor("moderator");
            if (order != null) {
                if (order.isAscending()) {
                    pageReviews = reviewRepository.findByModeratorIsNull(pageable);
                } else {
                    pageReviews = reviewRepository.findByModeratorIsNotNull(pageable);
                }
            } else {
                pageReviews = reviewRepository.findAll(pageable);
            }
        }
        return pageReviews;
    }

}