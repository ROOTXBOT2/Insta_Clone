package com.insta_clone.api;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@OpenAPIDefinition(
		info = @Info(
				title = "Insta_Clone API 명세서",
				version = "v1.0.0",
				description = "Insta_Clone 인스타그램 클론 백엔드 API 명세"
		)
)
@SpringBootApplication
public class InstaCloneApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstaCloneApiApplication.class, args);
	}

}
