package com.prince.qa.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.SkipException;

import java.net.UnknownHostException;

import static org.hamcrest.Matchers.*;

public class PostsApiTests {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

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

    private RequestSpecification request() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
    }

    @Test
    public void getAllPosts_shouldReturnPosts() {
        request().when().get("/posts").then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].id", notNullValue())
                .body("[0].title", notNullValue());
    }

    @Test(dataProvider = "postIds")
    public void getPostById_shouldReturnExpectedPost(int id) {
        request().pathParam("id", id).when().get("/posts/{id}").then()
                .statusCode(200)
                .body("id", equalTo(id))
                .body("userId", notNullValue())
                .body("title", notNullValue())
                .body("body", notNullValue());
    }

    @DataProvider(name = "postIds")
    public Object[][] postIds() {
        return new Object[][]{{1}, {2}, {5}};
    }

    @Test
    public void getUnknownPost_shouldReturn404() {
        Response response = request().when().get("/posts/999999");
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test
    public void createPost_shouldReturnCreatedResponse() {
        String payload = "{\"title\":\"API automation test\",\"body\":\"Created using REST Assured\",\"userId\":1}";
        request().body(payload).when().post("/posts").then()
                .statusCode(201)
                .body("title", equalTo("API automation test"))
                .body("body", equalTo("Created using REST Assured"))
                .body("userId", equalTo(1))
                .body("id", notNullValue());
    }

    @Test
    public void updatePost_shouldReturnUpdatedResponse() {
        String payload = "{\"id\":1,\"title\":\"Updated API test title\",\"body\":\"Updated with REST Assured\",\"userId\":1}";
        request().pathParam("id", 1).body(payload).when().put("/posts/{id}").then()
                .statusCode(200)
                .body("id", equalTo(1))
                .body("title", equalTo("Updated API test title"))
                .body("body", equalTo("Updated with REST Assured"));
    }

    @Test
    public void deletePost_shouldReturnSuccess() {
        request().pathParam("id", 1).when().delete("/posts/{id}").then()
                .statusCode(200);
    }
}
