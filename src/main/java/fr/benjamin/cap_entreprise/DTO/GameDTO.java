package fr.benjamin.cap_entreprise.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class GameDTO {

    @NotBlank(message = "Vous devez mettre un nom")
    private String name;

    @NotNull(message = "Vous devez choisir un editeur")
    @Min(value = 1, message = "Vous devez choisir un editeur")
    private Long publisherId;

    @NotBlank(message = "Vous devez choisir une date de publication")
    private String publishedAt;

    @NotBlank(message = "Vous devez mettre une description")
    private String description;

    @NotNull(message = "Vous devez choisir un genre")
    @Min(value = 1, message = "Vous devez choisir un genre")
    private Long genreId;

    @NotNull(message = "Vous devez choisir une classification")
    @Min(value = 1, message = "Vous devez choisir un classification")
    private Long classificationId;

    @Size(min = 1, message = "Vous devez choisir au moins une plateforme")
    private List<Long> platformIds = new ArrayList<>();

    @NotNull(message = "Vous devez choisir un modele economique")
    @Min(value = 1, message = "Vous devez choisir un modele economique")
    private Long businessModelId;

    @NotNull
    private Long moderatorId;

    private String image;

    @Override
    public String toString() {
        return "GameDTO{" +
                "name='" + name + '\'' +
                ", publisherId=" + publisherId +
                ", publishedAt=" + publishedAt +
                ", description='" + description + '\'' +
                ", genreId=" + genreId +
                ", classificationId=" + classificationId +
                ", platformIds=" + platformIds +
                ", businessModelId=" + businessModelId +
                ", moderatorId=" + moderatorId +
                '}';
    }
}
