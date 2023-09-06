package dev.afcasco.spring6restmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(
                                        antMatcher("/swagger-ui/**"),
                                        antMatcher("/v3/api-docs/**"),
                                        antMatcher("/swagger-ui.html"))
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(rs -> rs.jwt(Customizer.withDefaults()));
        //   .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"));
        return http.build();
    }
}
