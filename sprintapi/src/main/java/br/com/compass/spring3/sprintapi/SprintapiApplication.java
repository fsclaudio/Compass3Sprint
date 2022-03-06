package br.com.compass.spring3.sprintapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
@SpringBootApplication
public class SprintapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintapiApplication.class, args);
	}

}
