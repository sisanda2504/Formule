package za.ac.cput.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import za.ac.cput.security.jwt.JwtAuthenticationFilter;
import za.ac.cput.security.jwt.JwtAuthEntryPoint;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthEntryPoint jwtAuthEntryPoint;
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(withDefaults())

            // Disable CSRF for APIs
            .csrf(csrf -> csrf.disable())

            // Handle unauthorized access
            .exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthEntryPoint))

            // Use stateless sessions (JWT)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Authorize requests
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers(
                    "/formule/customer/create",
                    "/formule/manager/create",
                    "/formule/admin/create",
                    "/formule/product/**",
                    "/formule/category/**"
                ).permitAll()

                // Role-based access control
                .requestMatchers("/formule/admin/**").hasRole("ADMIN")
                .requestMatchers("/formule/manager/**").hasAnyRole("MANAGER", "ADMIN")
                .requestMatchers("/formule/customer/**").hasAnyRole("CUSTOMER", "ADMIN")

                // Everything else requires authentication
                .anyRequest().authenticated()
            )

            // Add JWT filter before username-password authentication
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // Authentication Manager bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // Password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}