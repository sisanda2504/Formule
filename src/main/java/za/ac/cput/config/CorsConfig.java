package za.ac.cput.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        // Allow local dev and Codespaces URLs
                        .allowedOrigins(
                            "http://localhost:5173",
                            "http://localhost:8080",
                            "https://stunning-guacamole-pqgx46pgqjg394r7-5173.app.github.dev",
                            "https://stunning-guacamole-pqgx46pgqjg394r7-8080.app.github.dev"
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}