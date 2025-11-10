package com.kartonobinsaleh.microservice.product_service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.hamcrest.Matchers;

import io.restassured.RestAssured;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

	static MongoDBContainer mongoDbContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDbContainer.start();
	}

	@Test
	void shouldCreateProduct() {
		String requestBody = """
				{
				    "name": "Iphone 17",
				    "description": "Iphone 17 is a smartphone from Apple",
				    "price": 3000
				}
				""";

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/api/products")
				.then()
				.statusCode(201)
				.body("id", Matchers.notNullValue())
				.body("name", Matchers.equalTo("Iphone 17"))
				.body("description", Matchers.equalTo("Iphone 17 is a smartphone from Apple"))
				.body("price", Matchers.equalTo(3000));
	}

}
