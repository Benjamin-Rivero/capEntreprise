package fr.benjamin.cap_entreprise.entity;

import fr.benjamin.cap_entreprise.entity.interfaces.NomenclatureInterface;
import fr.benjamin.cap_entreprise.entity.interfaces.SluggerInterface;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Platform implements SluggerInterface, NomenclatureInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String slug;

    @ManyToMany(mappedBy = "platforms")
    private List<Game> games;

    @Column(columnDefinition = "TEXT")
    private String logo;

    @Override
    public String getField() {
        return name;
    }
}