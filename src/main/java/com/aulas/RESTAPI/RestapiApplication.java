package com.aulas.RESTAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class RestapiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

}
