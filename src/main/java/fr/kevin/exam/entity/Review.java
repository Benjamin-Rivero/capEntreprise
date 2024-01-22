package fr.kevin.exam.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private LocalDateTime sendDate;

    private Float note;

    private LocalDateTime moderationDate;

    @ManyToOne
    private Game game;

    @ManyToOne
    private Player player;

    @ManyToOne
    @JoinColumn(name = "moderator_id")
    private Moderator moderator;

}