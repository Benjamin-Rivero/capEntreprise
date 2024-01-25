package fr.benjamin.cap_entreprise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.jasper.compiler.JspUtil;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("PLAYER")
public class Player extends User{

    private LocalDate birthDate;

    @OneToMany(mappedBy = "player")
    private List<Review> reviews;

}