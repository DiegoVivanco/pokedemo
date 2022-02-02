package com.example.pokedemo;

import com.example.pokedemo.utils.ApplicationConstants;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@OpenAPIDefinition(
		info = @Info(title = ApplicationConstants.API_TITLE, version = ApplicationConstants.VERSION))
public class PokedemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokedemoApplication.class, args);
	}

}
