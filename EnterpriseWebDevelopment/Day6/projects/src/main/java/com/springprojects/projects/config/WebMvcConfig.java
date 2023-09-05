package com.springprojects.projects.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
    @Bean
    public AuthenticationInterceptor customAuthenticationInterceptor() {
        return new AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customAuthenticationInterceptor())
                .addPathPatterns("/**") // Apply the interceptor to all paths
                .excludePathPatterns("/login"); // Exclude the login page from authentication
    }
}

