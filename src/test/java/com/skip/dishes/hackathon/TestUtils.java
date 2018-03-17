package com.skip.dishes.hackathon;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestUtils {

    public static final String API_URL = "http://localhost:8080/api/v1";

    public String login(String email, String password) throws Exception {
        Response api = RestAssured.given()
                .that().contentType("application/json")
                .when()
                .queryParam("email", email)
                .queryParam("password", password)
                .post(API_URL + "/Customer/auth");


        String accessToken = api.jsonPath().getString("access_token");

        return accessToken;
    }
}
