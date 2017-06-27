package com.cms4j;

import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class Cms4jApplication extends SpringBootServletInitializer {

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
		return (container -> {
			ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401");
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");

			container.addErrorPages(error401Page, error404Page, error500Page);
		});
	}
	public static void main(String[] args) {
		SpringApplication.run(Cms4jApplication.class, args);
	}
}
