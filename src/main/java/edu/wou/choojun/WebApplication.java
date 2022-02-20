package edu.wou.choojun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages={"edu.wou.choojun"})
@EnableJpaRepositories(basePackages="edu.wou.choojun.model")
@EnableTransactionManagement
@EntityScan(basePackages="edu.wou.choojun.entity")
public class WebApplication
{
	// modified from https://www.baeldung.com/spring-boot-crud-thymeleaf
	public static void main(String[] args)
	{
		SpringApplication.run(WebApplication.class, args);
	}

}
