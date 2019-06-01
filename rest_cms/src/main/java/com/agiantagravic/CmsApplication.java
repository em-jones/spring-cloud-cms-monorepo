package com.agiantagravic;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;


@SpringBootApplication
@ComponentScan("com.agiantagravic")
@EnableJpaAuditing
@EnableResourceServer
public class CmsApplication {

	public static void main(String[] args) {

		SpringApplication.run(CmsApplication.class, args);

	}

	@RequestMapping("/test")
	public String test() {
		return "passing";
	}

}
