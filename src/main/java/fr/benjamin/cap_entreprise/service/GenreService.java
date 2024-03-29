package fr.benjamin.cap_entreprise.service;

import fr.benjamin.cap_entreprise.entity.Genre;
import fr.benjamin.cap_entreprise.repository.GenreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GenreService implements SortingService<Genre>{

    private final GenreRepository genreRepository;

    public Genre findById(Long genreId) {
        return genreRepository.findById(genreId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public List<Genre> findAllSorted(Sort sort) {
        return genreRepository.findAll(sort);
    }
}