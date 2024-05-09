package com.example.demo.Config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class Configuration implements WebMvcConfigurer {
@Override
    public void addCorsMappings(CorsRegistry corsRegistry){
    corsRegistry.addMapping("/**")
            .allowedOrigins("http://172.20.10.2:8080")
            .allowedMethods("GET" ,"POST" ,"PUT" ,"DELETE")
            .allowedHeaders("*")
            .allowCredentials(true);
}


}
