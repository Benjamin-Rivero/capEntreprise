package fr.benjamin.cap_entreprise.repository;

import fr.benjamin.cap_entreprise.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

}