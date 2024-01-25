package fr.benjamin.cap_entreprise.configuration;

import fr.benjamin.cap_entreprise.mapping.UrlRoute;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

@Component
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth ->
                auth
                        .requestMatchers("/jeu/{id}/upload/game_image").hasRole("MODERATOR")
                        .requestMatchers("/avis/{id}/refuse").hasRole("MODERATOR")
                        .requestMatchers("/avis/{id}/validate").hasRole("MODERATOR")
                        .requestMatchers(UrlRoute.URL_REVIEW+"/**").authenticated()
                        .requestMatchers(HttpMethod.GET,UrlRoute.URL_GAME_NEW).hasRole("MODERATOR")
                        .requestMatchers(UrlRoute.URL_GAME+"/**").authenticated()
                        .requestMatchers("/**").permitAll()




            )
            .formLogin(formLogin ->
                formLogin
                    .loginPage("/login")
                    .permitAll()
            )
            .logout(logout ->
                 logout
                    .logoutSuccessUrl("/")
                    .permitAll()
            );

        return http.build();
    }

}
