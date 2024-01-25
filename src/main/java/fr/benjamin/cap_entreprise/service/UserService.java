package fr.benjamin.cap_entreprise.service;

import fr.benjamin.cap_entreprise.DTO.UserPostDTO;
import fr.benjamin.cap_entreprise.configuration.PasswordEncoderConfig;
import fr.benjamin.cap_entreprise.entity.Moderator;
import fr.benjamin.cap_entreprise.entity.Player;
import fr.benjamin.cap_entreprise.entity.User;
import fr.benjamin.cap_entreprise.exception.NotFoundEntityException;
import fr.benjamin.cap_entreprise.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoderConfig encoder;

    public User findById(Long id){

        return this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntityException("User","id",id));
    }

    public User findByUsername(String username){
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundEntityException("User","username",username));
    }
    public User create(UserPostDTO userForm) {
        Player user = new Player();
        user.setEmail(userForm.getEmail());
        user.setUsername(userForm.getUsername());
        user.setPassword(encoder.passwordEncoder().encode(userForm.getPassword()));
        user.setBirthDate(userForm.getBirthDate());
        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                userGrantedAuthority(user)
        );
    }

    private List<GrantedAuthority> userGrantedAuthority(User user) {
        if (user instanceof Moderator) {
            return List.of(new SimpleGrantedAuthority("ROLE_MODERATOR"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_PLAYER"));
    }

    public Collection<? extends GrantedAuthority> test(String username){
        UserDetails userDetails = loadUserByUsername(username);
        return userDetails.getAuthorities();
    }
}