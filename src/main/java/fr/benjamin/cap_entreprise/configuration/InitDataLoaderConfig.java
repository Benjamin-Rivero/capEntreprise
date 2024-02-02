package fr.benjamin.cap_entreprise.configuration;

import com.github.javafaker.Faker;
import fr.benjamin.cap_entreprise.entity.*;
import fr.benjamin.cap_entreprise.entity.interfaces.NomenclatureInterface;
import fr.benjamin.cap_entreprise.repository.*;
import fr.benjamin.cap_entreprise.service.*;
import fr.benjamin.cap_entreprise.utils.Slugger;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@Component
@AllArgsConstructor
public class InitDataLoaderConfig implements CommandLineRunner {

    private UserRepository userRepository;
    private UserService userService;

    private BusinessModelRepository businessModelRepository;
    private BusinessModelService businessModelService;

    private PlatformRepository platformRepository;
    private PlatformService platformService;

    private PublisherRepository publisherRepository;
    private PublisherService publisherService;

    private ClassificationRepository classificationRepository;
    private ClassificationService classificationService;

    private GenreRepository genreRepository;
    private GenreService genreService;

    private GameRepository gameRepository;
    private GameService gameService;

    private ReviewRepository reviewRepository;

    private BCryptPasswordEncoder passwordEncoder;

    private Slugger slugger;

    @Override
    public void run(String... args) {
        createUsers();
        createBusinessModels();
        createPlatforms();
        createPublishers();
        createClassifications();
        createGenres();
        userRepository.flush();
        createGames();
        gameRepository.flush();
        createReview();
        reviewRepository.flush();
    }

    private void createUsers() {
        Faker faker = new Faker(Locale.of("fr"));
        List<String> names = new ArrayList<>();
        for (long i = 1L; i <= 200; i++) {
            if(userRepository.findById(i).isEmpty()) {
                User user;
                if (i <= 5) {
                    user = new Moderator();
                    ((Moderator) user).setPhoneNumber(faker.phoneNumber().cellPhone());
                } else {
                    user = new Player();
                    LocalDate localDate = new java.sql.Date(faker.date().birthday(12, 60).getTime()).toLocalDate();
                    ((Player) user).setBirthDate(localDate);
                }
                user.setId(i);
                String name;
                do {
                    name = slugger.slugify(faker.funnyName().name().replace(" ", ""));
                } while (names.contains(name));
                names.add(name);
                user.setUsername(name);
                user.setEmail(faker.internet().safeEmailAddress());
                user.setPassword(passwordEncoder.encode("12345"));
                userRepository.save(user);
            }
        }
    }

    private void createBusinessModels() {
        createNomenclatures(
                businessModelRepository,
                BusinessModel.class,
                List.of("Free-to-Play", "Pay-to-Play", "Pay-to-win")
        );
    }

    private void createPlatforms() {
        List<String> platforms = List.of("Switch", "PC", "PS5", "PS4", "PS3", "XBOX Series X", "XBOX One");
        List<String> logo = List.of("<?xml version=\"1.0\" encoding=\"UTF-8\"?><svg width=\"24px\" height=\"24px\" viewBox=\"0 0 24 24\" stroke-width=\"1.5\" fill=\"none\" xmlns=\"http://www.w3.org/2000/svg\" color=\"#ffffff\"><path d=\"M2 17V7C2 4.79086 3.79086 3 6 3H9.9C10.2314 3 10.5 3.26863 10.5 3.6V20.4C10.5 20.7314 10.2314 21 9.9 21H6C3.79086 21 2 19.2091 2 17Z\" stroke=\"#ffffff\" stroke-width=\"1.5\"></path><path d=\"M6.5 8C7.05228 8 7.5 7.55228 7.5 7C7.5 6.44772 7.05228 6 6.5 6C5.94772 6 5.5 6.44772 5.5 7C5.5 7.55228 5.94772 8 6.5 8Z\" fill=\"#ffffff\" stroke=\"#ffffff\" stroke-width=\"1.5\" stroke-linecap=\"round\" stroke-linejoin=\"round\"></path><path d=\"M17.5 14C18.0523 14 18.5 13.5523 18.5 13C18.5 12.4477 18.0523 12 17.5 12C16.9477 12 16.5 12.4477 16.5 13C16.5 13.5523 16.9477 14 17.5 14Z\" fill=\"#ffffff\" stroke=\"#ffffff\" stroke-width=\"1.5\" stroke-linecap=\"round\" stroke-linejoin=\"round\"></path><path d=\"M22 17V7C22 4.79086 20.2091 3 18 3H14.1C13.7686 3 13.5 3.26863 13.5 3.6V20.4C13.5 20.7314 13.7686 21 14.1 21H18C20.2091 21 22 19.2091 22 17Z\" stroke=\"#ffffff\" stroke-width=\"1.5\"></path></svg>",
                "<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"24\" width=\"24\" viewBox=\"0 0 640 512\"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com/ License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path fill=\"#ffffff\" d=\"M384 96V320H64L64 96H384zM64 32C28.7 32 0 60.7 0 96V320c0 35.3 28.7 64 64 64H181.3l-10.7 32H96c-17.7 0-32 14.3-32 32s14.3 32 32 32H352c17.7 0 32-14.3 32-32s-14.3-32-32-32H277.3l-10.7-32H384c35.3 0 64-28.7 64-64V96c0-35.3-28.7-64-64-64H64zm464 0c-26.5 0-48 21.5-48 48V432c0 26.5 21.5 48 48 48h64c26.5 0 48-21.5 48-48V80c0-26.5-21.5-48-48-48H528zm16 64h32c8.8 0 16 7.2 16 16s-7.2 16-16 16H544c-8.8 0-16-7.2-16-16s7.2-16 16-16zm-16 80c0-8.8 7.2-16 16-16h32c8.8 0 16 7.2 16 16s-7.2 16-16 16H544c-8.8 0-16-7.2-16-16zm32 160a32 32 0 1 1 0 64 32 32 0 1 1 0-64z\"/></svg>",
                "<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"24\" width=\"24\" viewBox=\"0 0 576 512\" ><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com/ License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path fill=\"#ffffff\" d=\"M570.9 372.3c-11.3 14.2-38.8 24.3-38.8 24.3L327 470.2v-54.3l150.9-53.8c17.1-6.1 19.8-14.8 5.8-19.4-13.9-4.6-39.1-3.3-56.2 2.9L327 381.1v-56.4c23.2-7.8 47.1-13.6 75.7-16.8 40.9-4.5 90.9 .6 130.2 15.5 44.2 14 49.2 34.7 38 48.9zm-224.4-92.5v-139c0-16.3-3-31.3-18.3-35.6-11.7-3.8-19 7.1-19 23.4v347.9l-93.8-29.8V32c39.9 7.4 98 24.9 129.2 35.4C424.1 94.7 451 128.7 451 205.2c0 74.5-46 102.8-104.5 74.6zM43.2 410.2c-45.4-12.8-53-39.5-32.3-54.8 19.1-14.2 51.7-24.9 51.7-24.9l134.5-47.8v54.5l-96.8 34.6c-17.1 6.1-19.7 14.8-5.8 19.4 13.9 4.6 39.1 3.3 56.2-2.9l46.4-16.9v48.8c-51.6 9.3-101.4 7.3-153.9-10z\"/></svg>",
                "<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"24\" width=\"24\" viewBox=\"0 0 576 512\" ><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com/ License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path fill=\"#ffffff\" d=\"M570.9 372.3c-11.3 14.2-38.8 24.3-38.8 24.3L327 470.2v-54.3l150.9-53.8c17.1-6.1 19.8-14.8 5.8-19.4-13.9-4.6-39.1-3.3-56.2 2.9L327 381.1v-56.4c23.2-7.8 47.1-13.6 75.7-16.8 40.9-4.5 90.9 .6 130.2 15.5 44.2 14 49.2 34.7 38 48.9zm-224.4-92.5v-139c0-16.3-3-31.3-18.3-35.6-11.7-3.8-19 7.1-19 23.4v347.9l-93.8-29.8V32c39.9 7.4 98 24.9 129.2 35.4C424.1 94.7 451 128.7 451 205.2c0 74.5-46 102.8-104.5 74.6zM43.2 410.2c-45.4-12.8-53-39.5-32.3-54.8 19.1-14.2 51.7-24.9 51.7-24.9l134.5-47.8v54.5l-96.8 34.6c-17.1 6.1-19.7 14.8-5.8 19.4 13.9 4.6 39.1 3.3 56.2-2.9l46.4-16.9v48.8c-51.6 9.3-101.4 7.3-153.9-10z\"/></svg>",
                "<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"24\" width=\"24\" viewBox=\"0 0 576 512\" ><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com/ License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path fill=\"#ffffff\" d=\"M570.9 372.3c-11.3 14.2-38.8 24.3-38.8 24.3L327 470.2v-54.3l150.9-53.8c17.1-6.1 19.8-14.8 5.8-19.4-13.9-4.6-39.1-3.3-56.2 2.9L327 381.1v-56.4c23.2-7.8 47.1-13.6 75.7-16.8 40.9-4.5 90.9 .6 130.2 15.5 44.2 14 49.2 34.7 38 48.9zm-224.4-92.5v-139c0-16.3-3-31.3-18.3-35.6-11.7-3.8-19 7.1-19 23.4v347.9l-93.8-29.8V32c39.9 7.4 98 24.9 129.2 35.4C424.1 94.7 451 128.7 451 205.2c0 74.5-46 102.8-104.5 74.6zM43.2 410.2c-45.4-12.8-53-39.5-32.3-54.8 19.1-14.2 51.7-24.9 51.7-24.9l134.5-47.8v54.5l-96.8 34.6c-17.1 6.1-19.7 14.8-5.8 19.4 13.9 4.6 39.1 3.3 56.2-2.9l46.4-16.9v48.8c-51.6 9.3-101.4 7.3-153.9-10z\"/></svg>",
                "<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"24\" width=\"24\" viewBox=\"0 0 512 512\"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com/ License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path fill=\"#ffffff\" d=\"M369.9 318.2c44.3 54.3 64.7 98.8 54.4 118.7-7.9 15.1-56.7 44.6-92.6 55.9-29.6 9.3-68.4 13.3-100.4 10.2-38.2-3.7-76.9-17.4-110.1-39C93.3 445.8 87 438.3 87 423.4c0-29.9 32.9-82.3 89.2-142.1 32-33.9 76.5-73.7 81.4-72.6 9.4 2.1 84.3 75.1 112.3 109.5zM188.6 143.8c-29.7-26.9-58.1-53.9-86.4-63.4-15.2-5.1-16.3-4.8-28.7 8.1-29.2 30.4-53.5 79.7-60.3 122.4-5.4 34.2-6.1 43.8-4.2 60.5 5.6 50.5 17.3 85.4 40.5 120.9 9.5 14.6 12.1 17.3 9.3 9.9-4.2-11-.3-37.5 9.5-64 14.3-39 53.9-112.9 120.3-194.4zm311.6 63.5C483.3 127.3 432.7 77 425.6 77c-7.3 0-24.2 6.5-36 13.9-23.3 14.5-41 31.4-64.3 52.8C367.7 197 427.5 283.1 448.2 346c6.8 20.7 9.7 41.1 7.4 52.3-1.7 8.5-1.7 8.5 1.4 4.6 6.1-7.7 19.9-31.3 25.4-43.5 7.4-16.2 15-40.2 18.6-58.7 4.3-22.5 3.9-70.8-.8-93.4zM141.3 43C189 40.5 251 77.5 255.6 78.4c.7 .1 10.4-4.2 21.6-9.7 63.9-31.1 94-25.8 107.4-25.2-63.9-39.3-152.7-50-233.9-11.7-23.4 11.1-24 11.9-9.4 11.2z\"/></svg>",
                "<svg xmlns=\"http://www.w3.org/2000/svg\" height=\"24\" width=\"24\" viewBox=\"0 0 512 512\"><!--!Font Awesome Free 6.5.1 by @fontawesome - https://fontawesome.com/ License - https://fontawesome.com/license/free Copyright 2024 Fonticons, Inc.--><path fill=\"#ffffff\" d=\"M369.9 318.2c44.3 54.3 64.7 98.8 54.4 118.7-7.9 15.1-56.7 44.6-92.6 55.9-29.6 9.3-68.4 13.3-100.4 10.2-38.2-3.7-76.9-17.4-110.1-39C93.3 445.8 87 438.3 87 423.4c0-29.9 32.9-82.3 89.2-142.1 32-33.9 76.5-73.7 81.4-72.6 9.4 2.1 84.3 75.1 112.3 109.5zM188.6 143.8c-29.7-26.9-58.1-53.9-86.4-63.4-15.2-5.1-16.3-4.8-28.7 8.1-29.2 30.4-53.5 79.7-60.3 122.4-5.4 34.2-6.1 43.8-4.2 60.5 5.6 50.5 17.3 85.4 40.5 120.9 9.5 14.6 12.1 17.3 9.3 9.9-4.2-11-.3-37.5 9.5-64 14.3-39 53.9-112.9 120.3-194.4zm311.6 63.5C483.3 127.3 432.7 77 425.6 77c-7.3 0-24.2 6.5-36 13.9-23.3 14.5-41 31.4-64.3 52.8C367.7 197 427.5 283.1 448.2 346c6.8 20.7 9.7 41.1 7.4 52.3-1.7 8.5-1.7 8.5 1.4 4.6 6.1-7.7 19.9-31.3 25.4-43.5 7.4-16.2 15-40.2 18.6-58.7 4.3-22.5 3.9-70.8-.8-93.4zM141.3 43C189 40.5 251 77.5 255.6 78.4c.7 .1 10.4-4.2 21.6-9.7 63.9-31.1 94-25.8 107.4-25.2-63.9-39.3-152.7-50-233.9-11.7-23.4 11.1-24 11.9-9.4 11.2z\"/></svg>");
        for(long i=0;i<platforms.size();i++){
            Platform platform = new Platform();
            platform.setId(i+1);
            platform.setName(platforms.get((int)i));
            platform.setLogo(logo.get((int)i));
            platformRepository.save(platform);
        }
        /*createNomenclatures(
                platformRepository,
                Platform.class,
                List.of("Switch", "PC", "PS5", "PS4", "PS3", "XBOX Series X", "XBOX One")
        );*/
    }

    private void createPublishers() {
        createNomenclatures(
                publisherRepository,
                Publisher.class,
                List.of("Blizzard Entertainment", "Valve", "Riot Games", "Mojang", "Rockstar", "CD Projekt Red", "EA", "2k Games", "Ubisoft", "From Software", "Game Freak", "Nintendo", "Capcom")
        );
    }

    private void createGames() {
        Faker faker = new Faker(Locale.of("fr"));
        List<String> games = List.of("World of Warcraft", "Overwatch", "Diablo IV", "StarCraft II", "Warcraft III : reforged", "DotA 2", "Counter Strike 2", "Portal 2", "La League des Légendes", "Valorant", "Minecraft", "GTA V", "The witcher III", "Cyberpunk 2077", "Battlefield V", "Anno 1800", "Elden Ring", "Pokémon : Violet", "Pokémon : Ecarlate", "Zelda : Tears of the Kingdom", "Monster Hunter : World");
        List<Long> businessModels = List.of(2L, 2L, 2L, 2L, 2L, 1L, 2L, 2L, 3L, 1L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L);
        List<Long> publishers = List.of(1L, 1L, 1L, 1L, 1L, 2L, 2L, 2L, 3L, 3L, 4L, 5L, 6L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L);
        List<Long> genres = List.of(16L, 1L, 7L, 15L, 15L, 2L, 1L, 6L, 2L, 1L, 14L, 14L, 4L, 6L, 1L, 15L, 4L, 4L, 4L, 4L, 10L);
        List<Long> platforms = List.of(2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 2L, 1L, 1L, 1L, 2L);
        List<String> images = List.of("https://cdn.thegamesdb.net/images/thumb/boxart/front/149-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/32185-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/115193-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/151-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/803-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/2474-1.png", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f2/CS2_Cover_Art.jpg/220px-CS2_Cover_Art.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/914-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/928-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/72904-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/50424-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/20952-1.jpg", "https://calimacil.com/cdn/shop/files/Geralt-calimacil-larp-replica-banner-mobile.jpg?v=1695734545&width=1500", "https://cdn.thegamesdb.net/images/thumb/boxart/front/14517-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/55756-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/64422-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/65101-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/104566-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/104565-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/104362-1.jpg", "https://cdn.thegamesdb.net/images/thumb/boxart/front/60572-1.jpg");
        for (int i = 0; i < games.size(); i++) {
            Long id = (long) (i + 1);
            if (gameRepository.findById(id).isEmpty()) {
                Game game = new Game();
                game.setId(id);
                game.setName(games.get(i));
                game.setDescription("<h2>" + faker.yoda().quote() + "</h2></br>" + faker.lorem().paragraph(8));
                game.setImage(images.get(i));
                LocalDate localDate = new java.sql.Date(faker.date().birthday(2, 25).getTime()).toLocalDate();
                game.setPublishedAt(localDate);
                Random rand = new Random();
                game.setModerator((Moderator) userService.findById(rand.nextLong(6 - 1) + 1));
                game.setBusinessModel(businessModelService.findById(businessModels.get(i)));
                game.setClassification(classificationService.findById(rand.nextLong(4 - 1) + 1));
                game.setPublisher(publisherService.findById(publishers.get(i)));
                game.setGenre(genreService.findById(genres.get(i)));
                game.addPlatform(platformService.findById(platforms.get(i)));
                gameRepository.save(game);
            }
        }
    }

    private void createReview() {
        Faker faker = new Faker(Locale.of("fr"));
        for (long i = 1; i <= 500; i++) {
            if(reviewRepository.findById(i).isEmpty()) {
                Review review = new Review();
                review.setId(i);
                Random rand = new Random();
                LocalDateTime localDate = new java.sql.Date(faker.date().birthday(0, 2).getTime()).toLocalDate().atTime(0, 0);
                review.setCreatedAt(localDate);
                review.setPlayer((Player) userService.findById(rand.nextLong(201 - 6) + 6));
                review.setGame(gameService.findById(rand.nextLong(22 - 1) + 1));
                float rating = (float) rand.nextLong(21 - 2) + 2;
                if (review.getGame().getId().equals(9L)) {
                    rating = (float) rand.nextLong(3);
                } else if (review.getGame().getId().equals(6L) || review.getGame().getId().equals(1L)) {
                    rating = (float) rand.nextLong(21 - 10) + 10;
                }
                review.setRating(rating);
                review.setDescription("<strong>" + faker.chuckNorris().fact() + "</strong></br></br>" + faker.lorem().paragraph(3));
                if (i % 5 != 0) {
                    review.setModerator((Moderator) userService.findById(rand.nextLong(6 - 1) + 1));
                    review.setModeratedAt(LocalDateTime.now());
                }
                reviewRepository.save(review);
            }
        }
    }

    private void createClassifications() {
        createNomenclatures(
                classificationRepository,
                Classification.class,
                List.of("PEGI 3", "PEGI 7", "PEGI 12", "PEGI 16", "PEGI 18")
        );
    }

    private void createGenres() {
        createNomenclatures(
                genreRepository,
                Genre.class,
                List.of("FPS", "MOBA", "MMO", "RPG", "Voiture", "Aventure", "Hack'n'Slash", "Simulation", "Sport", "Action", "Horreur", "Plateforme", "Cartes", "Monde ouvert", "Stratégie", "MMO RPG")
        );
    }

    private void createNomenclatures(
            JpaRepository repository,
            Class<?> objectClass,
            List<String> items
    ) {
        items.forEach((name) -> {
            try {
                Long id = (long) items.indexOf(name) + 1;
                if (repository.findById(id).isEmpty()) {
                    Object item = objectClass.getDeclaredConstructor().newInstance();
                    if (item instanceof NomenclatureInterface nameEntity) {
                        nameEntity.setId(id);
                        nameEntity.setName(name);
                        repository.save(nameEntity);
                    }
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
