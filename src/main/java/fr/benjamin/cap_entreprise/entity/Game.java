package fr.benjamin.cap_entreprise.entity;

import fr.benjamin.cap_entreprise.entity.interfaces.SluggerInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
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

    @Column(nullable = false)
    private String name;

    private String slug;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private LocalDate publishedAt;

    private String image;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Genre genre;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Classification classification;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Publisher publisher;

    @ManyToMany
    @JoinTable(
            name = "game_platform",
            joinColumns = @JoinColumn(name = "game_id"),
            inverseJoinColumns = @JoinColumn(name = "platform_id")
    )
    private List<Platform> platforms = new ArrayList<>();

    @ManyToOne
    @JoinColumn(nullable = false)
    private BusinessModel businessModel;

    @OneToMany(mappedBy = "game",cascade = CascadeType.REMOVE)
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "moderator_id",nullable = false)
    private Moderator moderator;

    @Override
    public String getField() {
        return name;
    }


    public void addPlatform(Platform platform) {
        getPlatforms().add(platform);
    }
}