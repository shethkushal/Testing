package com.project.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/*@Bean
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2)
			.pathMapping("/*")
			.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo(){
		Contact contact = new Contact("Admin", "admin-creation.com", "admin@creation.edu");
		ApiInfo info = new ApiInfo("Spring App", "Spring Weather App", "1.0.0", "TnC", contact, "MIT", null, null);
		return info;
		
	}*/
}

