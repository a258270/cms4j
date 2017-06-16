package com.crm4j;

import com.crm4j.base.plugin.BaseSetting;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class Crm4jApplication {

	public static void main(String[] args) {
		SpringApplication.run(Crm4jApplication.class, args);
	}
}
