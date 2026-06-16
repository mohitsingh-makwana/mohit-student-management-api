package com.mohit.student_management_api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("/notice/**")
				.addResourceLocations("file:C:/Users/Asus/Desktop/DemoImageFolder/");
		
		registry.addResourceHandler("/notes/**")
		.addResourceLocations("file:C:/Users/Asus/Desktop/DemoImageFolder/");
		
		registry.addResourceHandler("/assignment/**")
				.addResourceLocations("file:C:/Users/Asus/Desktop/DemoImageFolder/assignment/");
	}
	
	
	
	
}
