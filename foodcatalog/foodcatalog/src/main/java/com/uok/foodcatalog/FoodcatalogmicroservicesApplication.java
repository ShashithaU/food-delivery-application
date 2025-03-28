package com.uok.foodcatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FoodcatalogmicroservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodcatalogmicroservicesApplication.class, args);
	}

	@Bean
	@LoadBalanced // This annotation is used to indicate that the RestTemplate bean should be load balanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
