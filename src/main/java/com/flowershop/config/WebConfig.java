package com.flowershop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Настройка глобального CORS
        registry.addMapping("/**")  // Разрешаем CORS для всех путей
                .allowedOrigins("http://localhost:3000") // Разрешаем только с этого домена
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Разрешаем указанные HTTP методы
                .allowedHeaders("*") // Разрешаем все заголовки
                .allowCredentials(true); // Разрешаем передачу куки
    }
}