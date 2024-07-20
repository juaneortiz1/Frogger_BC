package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.webConfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Especifica aquí el path de tu API
                .allowedOrigins("http://localhost:3000, *") // Permite solicitudes solo desde localhost:3000
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos HTTP permitidos
                .allowCredentials(true); // Permite el envío de credenciales (cookies, etc.) si es necesario
    }
}
