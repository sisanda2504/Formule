package za.ac.cput.formule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // disable for development/testing

            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers(
                    "/auth/**",
                    "/products/**",
                    "/categories/**",
                    "/"
                ).permitAll()

                // Admin-only endpoints
                .requestMatchers("/admin/**").hasRole("ADMIN")

                // Authenticated users only
                .requestMatchers(
                    "/cart/**",
                    "/checkout/**",
                    "/orders/**",
                    "/order/details/**",
                    "/customer/profile/**"
                ).authenticated()

                // All other requests require authentication
                .anyRequest().authenticated()
            )

            .httpBasic() // simple HTTP Basic for testing
            .and()
            .formLogin();

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}