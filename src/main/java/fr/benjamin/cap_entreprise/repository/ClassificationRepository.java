package fr.benjamin.cap_entreprise.repository;

import fr.benjamin.cap_entreprise.entity.Classification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificationRepository extends JpaRepository<Classification, Long> {

}