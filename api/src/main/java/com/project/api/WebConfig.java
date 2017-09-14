package com.project.api;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
//    @Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/api/**")
//			.allowedOrigins("http://mocker.egen.io/")
//			.allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS", "PATCH")
//			.allowedHeaders("header1", "header2", "header3")
//			.exposedHeaders("header1", "header2");
//	}
}
