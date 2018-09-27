package com.local.msvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author Tibu Padmakumar
 *
 */
@EnableAutoConfiguration(exclude = {
		// Add any configuration loading call you want to exclude

})
@EnableSwagger2
@SpringBootApplication
public class Application {

	/**
	 * @param args
	 *            -
	 */
	@SuppressWarnings({})
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

	}
}
