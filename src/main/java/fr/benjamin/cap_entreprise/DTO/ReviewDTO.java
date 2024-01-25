package fr.benjamin.cap_entreprise.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor@NoArgsConstructor@Getter@Setter
public class ReviewDTO {

    @NotNull(message = "Vous devez sélectionner un jeu")
    private Long gameId;

    @NotBlank(message = "Vous devez mettre une description")
    private String description;

    @NotNull(message = "Vous devez mettre une note")
    private Float rating;

    @NotNull(message = "Normalement devrait pas y avoir ce message d'erreur par contre ? ReviewDTO playerId")
    private Long playerId;

}
