package org.com.tasks.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.com.common.BaseTest.config;

public class Services {

    private static final String PEOPLE_ENDPOINT = config.BASE_ENDPOINT() + "people/";

    public Response getPeople() {
        RestAssured.useRelaxedHTTPSValidation();
        return given()
                .get(PEOPLE_ENDPOINT)

                .then().log().all().extract().response();
    }
}
