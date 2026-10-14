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

    @Test
    void shouldCreateAndGetCard() {
        // создаём карточку
        int id =
                given()
                        .contentType("application/json")
                        .body("{\"title\":\"TestCard\",\"description\":\"Created by API test\",\"status\":\"OPEN\"}")
                        .when().post("/cards")
                        .then()
                        .statusCode(anyOf(is(200), is(201)))
                        .extract().path("id");

        // проверяем, что карточка существует
        given()
                .when().get("/cards/" + id)
                .then()
                .statusCode(200)
                .body("title", equalTo("TestCard"))
                .body("status", equalTo("OPEN"));
    }
}
