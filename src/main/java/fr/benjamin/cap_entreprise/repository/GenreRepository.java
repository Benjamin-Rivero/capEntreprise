package fr.benjamin.cap_entreprise.repository;

import fr.benjamin.cap_entreprise.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

}