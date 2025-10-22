package com.ann;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class CardsApiTest {
    @BeforeAll
    static void setup() {
        String base = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test
    void shouldListCards() {
        given()
                .when().get("/cards")
                .then()
                .statusCode(200)
                .body("$", notNullValue());
    }
}

