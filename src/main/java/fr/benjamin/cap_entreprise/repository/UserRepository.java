package fr.benjamin.cap_entreprise.repository;

import fr.benjamin.cap_entreprise.entity.Player;
import fr.benjamin.cap_entreprise.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String nickname);
}