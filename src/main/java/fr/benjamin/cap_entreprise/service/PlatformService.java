package fr.benjamin.cap_entreprise.service;

import fr.benjamin.cap_entreprise.entity.Platform;
import fr.benjamin.cap_entreprise.repository.PlatformRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlatformService {

    private final PlatformRepository platformRepository;

    public Platform findById(Long platformId) {
        return platformRepository.findById(platformId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Platform> findAll() {
        return platformRepository.findAll();
    }
}