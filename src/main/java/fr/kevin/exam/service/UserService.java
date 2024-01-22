package fr.kevin.exam.service;

import fr.kevin.exam.DTO.UserPostDTO;
import fr.kevin.exam.entity.Player;
import fr.kevin.exam.entity.User;
import fr.kevin.exam.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public User create(UserPostDTO userForm) {
        User user = new Player();
        user.setEmail(userForm.getEmail());
        user.setNickname(userForm.getName());
        user.setPassword(userForm.getPassword());
        return userRepository.saveAndFlush(user);
    }
}