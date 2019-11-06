package com.healthy.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	public static final String SLASH_STAR = "/**";
	public static final String STAR = "*";

	@Bean
	public Docket greetingApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.healthy.controller"))

				.build().apiInfo(metaData());
//		    .securitySchemes(Lists.newArrayList(apiKey()))
//		    .securityContexts(Arrays.asList(securityContext()));
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Spring Boot REST API")
				.description("\"Spring Boot REST API for greeting people\"").version("1.0.0")
				.license("Apache License Version 2.0").licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
				.build();
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");

		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}

//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			// FIXME Shouldn't allowedOrigins("*")
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping(SLASH_STAR).allowedMethods(STAR).allowedOrigins(STAR);
//			}
//		};
//	}
}
