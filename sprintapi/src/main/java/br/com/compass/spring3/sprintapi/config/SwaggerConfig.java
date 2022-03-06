package br.com.compass.spring3.sprintapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.compass.spring3.sprintapi"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(appInfo());
	}
	
	private ApiInfo appInfo() {
		return new ApiInfoBuilder()
				.title("Compass Rest Api")
				.description("Sprint3 Api Exemplo")
				.version("1.0.0")
				.license("Apache Licence Version 3.0")
				.contact(new Contact("Cláudio","https://github.com/fsclaudio/fsclaudio",""))
				.build();
	} 
}