package com.prioritizareAti.prioritizareAti.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;


@Configuration
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH", "OPTIONS")
                .allowedOrigins("http") // http://localhost:5173
                .allowedHeaders("accessToken", "Authorization", "content-type")
                .exposedHeaders("accessToken", "Authorization")
                .allowCredentials(true)
                .maxAge(3600);
    }
}