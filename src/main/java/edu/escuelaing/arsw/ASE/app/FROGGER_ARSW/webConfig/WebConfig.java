package edu.escuelaing.arsw.ASE.app.FROGGER_ARSW.webConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Aplica a todas las rutas
                .allowedOriginPatterns("*") // Permite todos los orígenes que coincidan con el patrón "*"
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite estos métodos
                .allowedHeaders("*") // Permite todos los encabezados
                .allowCredentials(true); // Permite el envío de credenciales
    }

}
