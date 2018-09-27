package com.local.msvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AppConfig {

	@Value("${mongo.db.name}")
	private String dbName;

	@Value("${mongo.db.uri}")
	private String dbUri;

	@Bean
	public MongoDatabase getDbConnection(){
			
		return mongo().getDatabase(dbName);
	}
	
	@Bean
    public MongoClient mongo() {
        return new MongoClient(dbUri);
    }
	
	/**
	 * @return -
	 */
	@Bean
	public Docket documentation() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.local.msvc.controller")).build().pathMapping("/") //$NON-NLS-2$
				.apiInfo(metadata()).useDefaultResponseMessages(false);
	}


	/**
	 * 
	 * @return - the factory
	 */
	@Bean
	public UndertowEmbeddedServletContainerFactory underTowEmbeddedServletContainerFactory() {
		return new UndertowEmbeddedServletContainerFactory();
	}

	private ApiInfo metadata() {
		return new ApiInfoBuilder().title("Local Microservice") //$NON-NLS-1$
				.description("Template for local micro service") //$NON-NLS-1$
				.version("1.0.0") //$NON-NLS-1$
				.build();
	}
}
