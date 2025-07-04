package com.claro.claro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCOnfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Aplica a todas as rotas
                        .allowedOrigins("http://localhost:3000",
                                "https://front-squad36-claro-cqh823zfs-vanessas-projects-3185b83b.vercel.app",
                                "https://front-squad36-claro.vercel.app")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true); // Se estiver usando cookies/autenticação
            }
        };
    }
}
