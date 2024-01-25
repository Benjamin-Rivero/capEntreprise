package fr.benjamin.cap_entreprise.service;

import fr.benjamin.cap_entreprise.entity.Classification;
import fr.benjamin.cap_entreprise.repository.ClassificationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassificationService {

    private final ClassificationRepository classificationRepository;

    public Classification findById(Long classificationId) {
        return classificationRepository.findById(classificationId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Classification> findAll() {
        return classificationRepository.findAll();
    }
}