package com.art.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;
@Configuration
	public class WebConfig implements WebMvcConfigurer {

	    /**
	     * Configures global CORS mappings for the application.
	     * Allows all endpoints to accept requests from specific origins and with certain methods.
	     *
	     * @param registry - The CORS registry to configure.
	     */
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
	        registry.addMapping("/**")  // Apply to all endpoints in the application
	                .allowedOrigins("http://localhost:3000")  // Allow requests only from React's development server (frontend)
	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow specific HTTP methods
	                .allowedHeaders("*")  // Allow all headers
	                .allowCredentials(true)  // Allow credentials (cookies, authorization headers, etc.)
	                .maxAge(3600);  // Cache CORS response for 1 hour (in seconds)
	    }
	}
