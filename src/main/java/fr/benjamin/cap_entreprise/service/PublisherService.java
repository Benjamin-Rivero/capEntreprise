package fr.benjamin.cap_entreprise.service;

import fr.benjamin.cap_entreprise.entity.Publisher;
import fr.benjamin.cap_entreprise.exception.NotFoundEntityException;
import fr.benjamin.cap_entreprise.repository.PublisherRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherService {

    private PublisherRepository publisherRepository;

    public Publisher findById(Long publisherId) {
        return publisherRepository.findById(publisherId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Publisher> findAll() {
        return this.publisherRepository.findAll();
    }
}