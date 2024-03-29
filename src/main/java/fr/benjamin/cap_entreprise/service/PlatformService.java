package fr.benjamin.cap_entreprise.service;

import fr.benjamin.cap_entreprise.entity.Platform;
import fr.benjamin.cap_entreprise.repository.PlatformRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlatformService implements SortingService<Platform>{

    private final PlatformRepository platformRepository;

    public Platform findById(Long platformId) {
        return platformRepository.findById(platformId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<Platform> findAll() {
        return platformRepository.findAll();
    }

    @Override
    public List<Platform> findAllSorted(Sort sort) {
        return platformRepository.findAll(sort);
    }

    public List<String> getPlatformLogos(List<Platform> platforms){
        List<String> logos = new ArrayList<>();
        platforms.forEach(platform -> {
            if(!logos.contains(platform.getLogo())){
                logos.add(platform.getLogo());
            }
        });
        return logos;
    }
}