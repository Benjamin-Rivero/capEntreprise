package fr.benjamin.cap_entreprise.service;

import fr.benjamin.cap_entreprise.entity.BusinessModel;
import fr.benjamin.cap_entreprise.repository.BusinessModelRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BusinessModelService {

    private final BusinessModelRepository businessModelRepository;

    public BusinessModel findById(Long businessModelId) {
        return businessModelRepository.findById(businessModelId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public List<BusinessModel> findAll() {
        return businessModelRepository.findAll();
    }
}