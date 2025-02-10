package com.art.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
	public class WebConfig implements WebMvcConfigurer {

	    /**
	     * Configures global CORS mappings for the application.
	     * Allows all endpoints to accept requests from specific origins and with certain methods.
	     *
	     * @param registry - The CORS registry to configure.
	     */
//	    @Override
//	    public void addCorsMappings(CorsRegistry registry) {
//	        registry.addMapping("/**")  // Apply to all endpoints in the application
//	                .allowedOrigins("http://localhost:3000")  // Allow requests only from React's development server (frontend)
//	                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allow specific HTTP methods
//	                .allowedHeaders("*")  // Allow all headers
//	                .allowCredentials(true)  // Allow credentials (cookies, authorization headers, etc.)
//	                .maxAge(3600);  // Cache CORS response for 1 hour (in seconds)
//	    }
	
	 @Bean
	    public CorsFilter corsFilter() {
	        CorsConfiguration corsConfig = new CorsConfiguration();
	        corsConfig.setAllowCredentials(true);
	        corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000")); // Allow frontend
	        corsConfig.setAllowedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Authorization"));
	        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", corsConfig);
	        return new CorsFilter(source);
	    }
	
	
	}
