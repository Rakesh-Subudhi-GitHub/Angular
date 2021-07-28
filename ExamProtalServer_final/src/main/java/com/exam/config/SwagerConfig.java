package com.exam.config;

import javax.persistence.criteria.Predicate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwagerConfig {

	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
							.groupName("public-api")
							.apiInfo(apiInfo())
							.select()
							//.apis(RequestHandlerSelectors.basePackage("com.rk.controller"))
							.paths(PathSelectors.any())
							.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
							.title("UserManagement API")
							.description("Rakesh API reference for developers")
							.termsOfServiceUrl("http://www.rakesh.in")
							.contact("Rakesh.subudhi50@gmail.com")
							.license("Rakesh License")
							.licenseUrl("Rakesh.subudhi50@gmail.com")
							.version("1.0")
							.build();
	}
}//class
