package edu.senai.consultorio_backend.pacientes_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configures the security settings for the application.
 * This class enables web security and defines the security filter chain that
 * determines which requests require authentication.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Defines the security filter chain for the application.
     * This method configures the HttpSecurity object to require authentication
     * for all requests to "/api/**" and permits all other requests. It also
     * configures the application as an OAuth 2.0 Resource Server using opaque
     * token validation.
     *
     * @param http The HttpSecurity object to be configured.
     * @return The configured SecurityFilterChain object.
     * @throws Exception if an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/api/**").authenticated()
                .anyRequest().permitAll()
            )
            .oauth2ResourceServer(oauth2 -> oauth2.opaqueToken());
        return http.build();
    }
}
