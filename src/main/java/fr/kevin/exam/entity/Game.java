package fr.kevin.exam.entity;

import fr.kevin.exam.entity.interfaces.SluggerInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Game implements SluggerInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String slug;

    private String description;

    private LocalDate releaseDate;

    private String image;

    @ManyToOne
    private Genre genre;

    @ManyToOne
    private Classification classification;

    @ManyToOne
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "game_platform",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private List<Platform> platforms;

    @ManyToOne
    private BusinessModel businessModel;

    @OneToMany(mappedBy = "game")
    private List<Review> reviews;

    @ManyToOne
    @JoinColumn(name = "moderator_id")
    private Moderator moderator;

    @Override
    public String getField() {
        return name;
    }
}