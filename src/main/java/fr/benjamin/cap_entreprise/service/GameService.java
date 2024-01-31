package fr.benjamin.cap_entreprise.service;

import fr.benjamin.cap_entreprise.DTO.GameDTO;
import fr.benjamin.cap_entreprise.entity.Game;
import fr.benjamin.cap_entreprise.entity.Moderator;
import fr.benjamin.cap_entreprise.entity.Platform;
import fr.benjamin.cap_entreprise.exception.NotFoundEntityException;
import fr.benjamin.cap_entreprise.repository.GameRepository;
import fr.benjamin.cap_entreprise.utils.DateUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GameService implements SortingService<Game>{

    private final GameRepository gameRepository;
    private final PublisherService publisherService;
    private final GenreService genreService;
    private final ClassificationService classificationService;
    private final PlatformService platformService;
    private final BusinessModelService businessModelService;
    private final UserService userService;

    public Game findById(Long gameId) {
        return this.gameRepository.findById(gameId)
                .orElseThrow(() -> new NotFoundEntityException("Game","id",gameId));
    }

    public List<Game> findAll() {
        return this.gameRepository.findAll();
    }

    public Game persist(GameDTO dto, Long id) {
        Game game = new Game();
        game.setId(id);
        game.setName(dto.getName());
        game.setPublisher(publisherService.findById(dto.getPublisherId()));
        game.setPublishedAt(LocalDate.parse(dto.getPublishedAt()));
        game.setDescription(dto.getDescription());
        game.setGenre(genreService.findById(dto.getGenreId()));
        game.setClassification(classificationService.findById(dto.getClassificationId()));
        dto.getPlatformIds().forEach(platformId->{
            game.getPlatforms().add(platformService.findById(platformId));
        });
        game.setBusinessModel(businessModelService.findById(dto.getBusinessModelId()));
        game.setModerator((Moderator)userService.findById(dto.getModeratorId()));
        game.setImage(dto.getImage());
        return this.gameRepository.saveAndFlush(game);
    }

    public void test(){
        gameRepository.findTop20ByOrderByCountReview().forEach(game -> {
            System.out.println(game.getId());
        });
    }

    public GameDTO getDTOById(Long id) {
        Game game = findById(id);
        GameDTO dto = new GameDTO();
        dto.setName(game.getName());
        dto.setPublisherId(game.getPublisher().getId());
        dto.setDescription(game.getDescription());
        dto.setPublishedAt(DateUtils.convertLocalDateToFormat(game.getPublishedAt(),"yyyy-MM-dd"));
        dto.setGenreId(game.getGenre().getId());
        dto.setClassificationId(game.getClassification().getId());
        dto.setPlatformIds(game.getPlatforms().stream().map(Platform::getId).collect(Collectors.toList()));
        dto.setBusinessModelId(game.getBusinessModel().getId());
        dto.setModeratorId(game.getModerator().getId());
        dto.setImage(game.getImage());
        return dto;
    }

    public void delete(Long id){
        this.gameRepository.deleteById(id);
    }

    public Page<Game> findAll(Pageable pageable) {
        return gameRepository.findAll(pageable);
    }

    @Override
    public List<Game> findAllSorted(Sort sort) {
        return gameRepository.findAll(sort);
    }

    public List<Game> getRandomGames() {
        List<Game> randoms = new ArrayList<>();
        List<Game> games = gameRepository.findAll();
        while(randoms.size()<4){
            int selected = (int)Math.floor(Math.random()*(games.size()));
            randoms.add(games.get(selected));
            games.remove(selected);
        }
        return randoms;
    }
}