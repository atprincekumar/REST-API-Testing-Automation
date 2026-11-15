package com.prince.qa.base;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.SkipException;
import org.testng.annotations.BeforeClass;

import java.net.UnknownHostException;

public class BaseApiTest {
    protected static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    @BeforeClass
    public void configureApi() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        try {
            java.net.InetAddress.getByName("jsonplaceholder.typicode.com");
        } catch (UnknownHostException e) {
            throw new SkipException(
                    "Skipping API tests because jsonplaceholder.typicode.com cannot be resolved. " +
                    "Check internet and DNS connectivity.", e);
        }
    }

    protected RequestSpecification request() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }
}
