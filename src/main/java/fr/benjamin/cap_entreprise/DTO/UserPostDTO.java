package fr.benjamin.cap_entreprise.DTO;

import fr.benjamin.cap_entreprise.validation.annotation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor
public class UserPostDTO {

    @Email(message = "Veuillez mettre un email valide")
    @NotBlank(message = "Veuillez mettre un email")
    private String email;

    @NotBlank(message = "Veuillez mettre un nom de compte")
    @Size(message = "Le nom du compte doit avoir 5 caractères minimum", min = 5)
    @UniqueUsername
    private String username;

    @NotBlank(message = "Veuillez mettre un mot de passe")
    @Size(message = "Le mot de passe doit avoir 5 caractères minimum", min = 5)
    private String password;

    @NotNull
    private LocalDate birthDate;

}
