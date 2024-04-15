package com.example.demo.Config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class Configuration implements WebMvcConfigurer {
@Override
    public void addCorsMappings(CorsRegistry corsRegistry){
    corsRegistry.addMapping("/**")
            .allowedOrigins("http://localhost:58672")
            .allowedMethods("GET" ,"POST" ,"PUT" ,"DELETE")
            .allowedHeaders("*")
            .allowCredentials(true);
}


}
