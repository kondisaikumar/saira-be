package com.saira.sairarestful.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configure CORS for all endpoints (you can customize the path or methods if needed)
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")  // Allow Angular frontend to make requests
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")  // Allow all HTTP methods
                .allowedHeaders("*")  // Allow all headers
                .allowCredentials(true);  // Allow credentials (cookies, authorization headers, etc.)
    }
}
