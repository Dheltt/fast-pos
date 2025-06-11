package com.fast_pos.fast_pos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.metamodel.Metamodel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.fast_pos.fast_pos.adapter.out.persistence.entity")
@EnableJpaRepositories("com.fast_pos.fast_pos.adapter.out.persistence.jpa")
@OpenAPIDefinition(info = @Info(title = "My API", version = "v1"))
@ComponentScan(basePackages = "com.fast_pos.fast_pos")
public class FastPosApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastPosApplication.class, args);
	}

	@Bean
	public CommandLineRunner checkEntities(EntityManagerFactory entityManagerFactory) {
		return args -> {
			Metamodel metamodel = entityManagerFactory.getMetamodel();
			System.out.println("\n\n=================== JPA ENTITIES ===================");
			metamodel.getEntities().forEach(e -> System.out.println("✅ ENTITY -> " + e.getName()));
			System.out.println("====================================================\n\n");
			metamodel.getEntities().forEach(e -> System.out.println("✅ " + e.getName()));
		};
	}
}
