package com.univeristy.ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "com.univeristy.ms.controller", "com.univeristy.ms.service","com.univeristy.ms.exception", "com.univeristy.ms.config"})
@EntityScan("com.univeristy.ms.entity")
@EnableJpaRepositories("com.univeristy.ms.repository")
@EnableFeignClients("com.univeristy.ms.feignclients")
public class StudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}

}
